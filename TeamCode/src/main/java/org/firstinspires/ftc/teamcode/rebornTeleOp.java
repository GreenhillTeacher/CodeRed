/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
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


