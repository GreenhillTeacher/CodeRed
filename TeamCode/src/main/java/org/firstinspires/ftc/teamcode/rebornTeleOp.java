/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * 
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


//  DRIVING MECANUM WHEELS SIMPLIFIED         
//             @TeleOp
//             public class MecanumTeleOp extends LinearOpMode {
//                 @Override
//                 public void runOpMode() throws InterruptedException {
//                     // Declare our motors
//                     // Make sure your ID's match your configuration
//                     // call hardware class here


//                     waitForStart();

//                     if (isStopRequested()) return;

//                     while (opModeIsActive()) {
//                         double y = -gamepad1.left_stick_y; // Remember, this is reversed!
//                         double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//                         double rx = gamepad1.right_stick_x;

//                         // Denominator is the largest motor power (absolute value) or 1
//                         // This ensures all the powers maintain the same ratio, but only when
//                         // at least one is out of the range [-1, 1]
//                         double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//                         double frontLeftPower = (y + x + rx) / denominator;
//                         double backLeftPower = (y - x + rx) / denominator;
//                         double frontRightPower = (y - x - rx) / denominator;
//                         double backRightPower = (y + x - rx) / denominator;

//                         motorFrontLeft.setPower(frontLeftPower);
//                         motorBackLeft.setPower(backLeftPower);
//                         motorFrontRight.setPower(frontRightPower);
//                         motorBackRight.setPower(backRightPower);
//                     }
//                 }
//             }
/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 */

@TeleOp(name="teleOpTest-Mechanum", group="reborn") // i will come soon. when the 3d print is done, make sure u watch ur back
//@Disabled
public class rebornTeleOp extends OpMode {

    rebornHardware robot = new rebornHardware();
    // Declare OpMode members.

    //methods to control the speed of the robot.
    private float speedModifier = .5f;
    private float reductionModifier = .3f;//the amount that the speed will be decreased in precision mode. Should be < 1
    private float turboModifier = 1.5f;// the amount that the speed will be increased in turbo mode. Must be <2. No increase is 1.
    private float precisionActive = 1f;
    private float turnReduction = .5f;//reduces the speed of turning. <1 to reduce. 1 if to leave as normal> yuh
    //private float BRDrive = 1f;

    @Override
    public void init()
    {
        //Initialize the hardware variables.
        //The init() method of the hardware class does all the work here
        robot.init(hardwareMap);
    }

    @Override
    public void loop()

    {
        mecanumMove();
    }

    public void mecanumMove()
    {
        //======================================
        //------------WHEEL CODE----------------
        //======================================
        {
            //variables
            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y); //ur mom is watching you from the ceiling. dont look up...
            double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x * turnReduction;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX; //the swedes are coming 4 u soon
            final double v4 = r * Math.cos(robotAngle) - rightX;


            if (gamepad1.left_bumper) {//if the left bumper is pressed, it multiplies the total power by the precision driving modifer
                precisionActive = reductionModifier;
            } else if (gamepad1.right_bumper) {
                precisionActive = turboModifier;//right bumper = turbo mode (for crossing the barriers)
            } else {
                precisionActive = 1f; //no modifier
            }


            robot.frontLeft.setPower(-speedModifier * v1 * precisionActive);
            robot.frontRight.setPower(-speedModifier * v2 * precisionActive);
            robot.backLeft.setPower(-speedModifier * v3 * precisionActive);
            robot.backRight.setPower(-speedModifier * v4 * precisionActive);

            telemetry.addData("fLPower", -speedModifier * v1 * precisionActive);
            telemetry.addData("fRPower", -speedModifier * v2 * precisionActive);
            telemetry.addData("bLPower", -speedModifier * v3 * precisionActive);
            telemetry.addData("bRPower", -speedModifier * v4 * precisionActive);

            telemetry.addData("Encoder port 1 back left", robot.backLeft.getCurrentPosition());
            telemetry.addData("Encoder port 2 front right", robot.frontRight.getCurrentPosition());
            telemetry.addData("Encoder port 3 back right", robot.backRight.getCurrentPosition());
            telemetry.addData("Encoder port 4 back left", robot.backLeft.getCurrentPosition());

        }

        telemetry.addLine();

        //======================================
        //----------QUACK DELIVERY--------------
        //======================================

        if (gamepad1.y){
            robot.duckSpinner.setPower(1);
            telemetry.addData("Duck Spinner", "Wheeeee");
        }
        else if (gamepad1.x){
            robot.duckSpinner.setPower(-1);
            telemetry.addData("Duck Spinner", "Down"); //hello. i am watching.

        }
        else {
            robot.duckSpinner.setPower(0);
            telemetry.addData("Duck Spinner", "Off"); //ripped hamilton is near
        }

        //======================================
        //----------CLAW ROTATOR----------------
        //======================================

        if (gamepad1.dpad_left){
            robot.rotateRight.setPower(-.2*precisionActive);
            robot.rotateLeft.setPower(-.2*precisionActive);
            telemetry.addData("Rotator State", "Down"); //be careful wherever you go.
        }
        else if (gamepad1.dpad_right){
            robot.rotateRight.setPower(.15*precisionActive);
            robot.rotateLeft.setPower(.15*precisionActive);
            telemetry.addData("Rotator State", "Up");

        }
        else {
            robot.rotateRight.setPower(0);
            robot.rotateLeft.setPower(0);
            telemetry.addData("Rotator State", "Off"); // r u watching behind you
        }

        telemetry.addLine();
        telemetry.addData("Claw Rotator Position:", robot.rotateRight.getCurrentPosition());
        telemetry.addData("Claw Rotator Position:", robot.rotateLeft.getCurrentPosition());

        //======================================
        //----------LIFT CONTROL----------------
        //======================================

        if (gamepad1.dpad_up){
            robot.liftMotor.setPower(.4);
            telemetry.addData("Extender Star Symbol", "Stretchy Boi");
        }
        else if (gamepad1.dpad_down){
            robot.liftMotor.setPower(-.2);
            telemetry.addData("Extender Star Symbol", "Shrinky Boi");
        }
        else {
            robot.liftMotor.setPower(0);
            telemetry.addData("Extender Star Symbol", "Bench Warming");//Bench Warming = not doin' anythin
        }

        //======================================
        //----------CLAW ACTIVATION-------------
        //======================================

        if (gamepad1.right_trigger>.1){
            robot.clawServo.setPosition(0);
            telemetry.addData("Claw State", "Squeeeze");//squeeze is close
        }
        else{
            robot.clawServo.setPosition(.15);
            telemetry.addData("Claw State", "Sigh");//sigh is release
        }
        // LIFT ACTIVATION
        // up and down using left trigger for extension/lift
        if (gamepad1.left_trigger>.1){
            robot.dropServo.setPosition(0);
            telemetry.addData("helo boogaloo", "byebye boogaloo");//bye bye boogalo is down
        }
        else{
            robot.dropServo.setPosition(.5);
            telemetry.addData("helo boogaloo", "hii boogaloo");//hii boogaloo is up
        }

        telemetry.update();
    }
}


