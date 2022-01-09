package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class rebornDriving extends LinearOpMode {


    rebornHardware robot = new rebornHardware();   // Use a Pushbot's hardware

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    final double DESIRED_DISTANCE = 8.0; //  this is how close the camera should get to the target (inches)
    //  The GAIN constants set the relationship between the measured position error,
    //  and how much power is applied to the drive motors.  Drive = Error * Gain
    //  Make these values smaller for smoother control.
    final double SPEED_GAIN =   0.02 ;   //  Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double TURN_GAIN  =   0.01 ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MM_PER_INCH = 25.40 ;   //  Metric conversion

    @Override
    public void runOpMode(){

    }

    public void motorStop() {
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
//        robot.carousel.setPower(0);
        reset();
    }
    public void reset(){
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void move(double power, char direction, double distance) {
        double ticks = COUNTS_PER_INCH * distance/3;
//        double ticks = 7.5* distance;
        switch(direction){
            case 'f':
                //to go forward

                //set target position
                robot.frontLeft.setTargetPosition((int)ticks);
                robot.backLeft.setTargetPosition((int)(ticks));
                robot.frontRight.setTargetPosition((int)(ticks));
                robot.backRight.setTargetPosition((int)(ticks));
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(power);
                robot.frontRight.setPower(power);
                robot.backLeft.setPower(power);
                robot.backRight.setPower(power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;

            case 'b':
                //setting power of motors to go backward


                //set target position
                robot.frontLeft.setTargetPosition((int) -ticks);
                robot.backLeft.setTargetPosition((int) -ticks);
                robot.frontRight.setTargetPosition((int) -ticks);
                robot.backRight.setTargetPosition((int) -ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(-power);
                robot.frontRight.setPower(-power);
                robot.backLeft.setPower(-power);
                robot.backRight.setPower(-power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {
                    telemetry.clear();
                    telemetry.addData("Front Left Pos", robot.frontLeft.getCurrentPosition());
                    telemetry.addData("Front Right Pos", robot.frontRight.getCurrentPosition());
                    telemetry.addData("Back Left Pos", robot.backLeft.getCurrentPosition());
                    telemetry.addData("Back Right (Mephistopheles) Pos", robot.backRight.getCurrentPosition());
                    telemetry.update();

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;

            case 'r':
                //to turn clockwise

                robot.frontLeft.setTargetPosition((int)ticks);
                robot.backLeft.setTargetPosition((int)ticks);
                robot.frontRight.setTargetPosition((int)-ticks);
                robot.backRight.setTargetPosition((int)-ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(power);
                robot.frontRight.setPower(power);
                robot.backLeft.setPower(-power);
                robot.backRight.setPower(-power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;
            case 'l':
                // to turn counter clockwise

                robot.frontLeft.setTargetPosition((int)ticks);
                robot.backLeft.setTargetPosition((int) -ticks);
                robot.frontRight.setTargetPosition((int)-ticks);
                robot.backRight.setTargetPosition((int) ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(-power);
                robot.frontRight.setPower(power);
                robot.backLeft.setPower(-power);
                robot.backRight.setPower(power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {
                    telemetry.clear();
                    telemetry.addData("Front Left Pos", robot.frontLeft.getCurrentPosition());
                    telemetry.addData("Front Right Pos", robot.frontRight.getCurrentPosition());
                    telemetry.addData("Back Left Pos", robot.backLeft.getCurrentPosition());
                    telemetry.addData("Back Right (Mephistopheles) Pos", robot.backRight.getCurrentPosition());
                    telemetry.update();
                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;
            case 'x':
                //to strafe right


                //set target position
                robot.frontLeft.setTargetPosition((int) ticks);
                robot.backLeft.setTargetPosition((int)-ticks);
                robot.frontRight.setTargetPosition((int)-ticks);
                robot.backRight.setTargetPosition((int) ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(power);
                robot.frontRight.setPower(-power);
                robot.backLeft.setPower(-power);
                robot.backRight.setPower(power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;
            case 'y' :
                // to strafe left

                //set target position
                robot.frontLeft.setTargetPosition((int)-ticks);
                robot.backLeft.setTargetPosition((int)ticks);
                robot.frontRight.setTargetPosition((int)ticks);
                robot.backRight.setTargetPosition((int)-ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(-power);
                robot.frontRight.setPower(power);
                robot.backLeft.setPower(power);
                robot.backRight.setPower(-power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy())
                {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                break;

            default:
                motorStop();
        }
    }
    public void diagonal(double power, char direction, long distance){
        double ticks = 1120/7.5 * distance;
        switch(direction) {
            case '1':
                //forward right

                //set target position

                robot.frontLeft.setTargetPosition((int) (ticks));
                robot.backLeft.setTargetPosition(0);
                robot.frontRight.setTargetPosition(0);
                robot.backRight.setTargetPosition((int) ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(power);
                robot.frontRight.setPower(0);
                robot.backLeft.setPower(0);
                robot.backRight.setPower(power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy()) {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;

            case '2':
                //forward left

                //set target position

                robot.frontLeft.setTargetPosition(0);
                robot.backLeft.setTargetPosition((int) ticks);
                robot.frontRight.setTargetPosition((int) ticks);
                robot.backRight.setTargetPosition(0);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(power);
                robot.backLeft.setPower(power);
                robot.backRight.setPower(0);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy()) {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                break;
            case '3':
                // go back right

                robot.frontLeft.setTargetPosition(0);
                robot.backLeft.setTargetPosition((int) -ticks);
                robot.frontRight.setTargetPosition((int) -ticks);
                robot.backRight.setTargetPosition(0);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(-power);
                robot.backLeft.setPower(-power);
                robot.backRight.setPower(0);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy()) {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                break;

            case '4':
//back left
                robot.frontLeft.setTargetPosition((int) -ticks);
                robot.backLeft.setTargetPosition(0);
                robot.frontRight.setTargetPosition(0);
                robot.backRight.setTargetPosition((int) -ticks);
                //set run to position
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //set drive power for forward
                robot.frontLeft.setPower(-power);
                robot.frontRight.setPower(0);
                robot.backLeft.setPower(0);
                robot.backRight.setPower(-power);

                while (robot.frontLeft.isBusy() && robot.backLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy()) {

                }
                motorStop();
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                break;
            default:
                motorStop();

        }
    }
}
