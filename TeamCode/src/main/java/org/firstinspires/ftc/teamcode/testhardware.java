package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// Hardware class for the Greenhill 3 testbot

public class testhardware
{
    /* Public OpMode members. */
    public DcMotor  frontLeft   = null, frontRight  = null, backLeft   = null, backRight  = null;
    //public DcMotor  pulleyMotor0 = null, pulleyMotor1=null, carousel = null;
    //public CRServo extenderServo = null;
    //public Servo grabberServo = null;

    public static final double grabber_min = 0;
    public static final double grabber_max = 0.75;

    /* Constructor */
    public testhardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap) {
        // Define and Initialize Devices
        frontLeft  = hwMap.get(DcMotor.class, "front_left");
        frontRight = hwMap.get(DcMotor.class, "front_right");
        backLeft   = hwMap.get(DcMotor.class, "back_left");
        backRight  = hwMap.get(DcMotor.class, "back_right");



        // Set Direction
        frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        // RUN TO POSITION
        // Set all motors to run with encoders if applicable.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
}

