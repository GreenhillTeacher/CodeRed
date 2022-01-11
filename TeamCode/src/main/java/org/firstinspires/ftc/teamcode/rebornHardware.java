package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// Hardware class for the Greenhill 3 testbot
// This hardware is for INFERNO REBORN, the robot we were working on as of January 8, 2022.

public class rebornHardware
{
    /* Public OpMode members. */
    public DcMotor  frontLeft   = null, frontRight  = null, backLeft   = null, backRight  = null, rotateLeft = null, rotateRight = null;
    //public DcMotor  pulleyMotor0 = null, pulleyMotor1=null, carousel = null;
    //public CRServo extenderServo = null;
    //public Servo grabberServo = null;

//    public static final double grabber_min = 0;
//    public static final double grabber_max = 0.75;

    /* Constructor */
    public rebornHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap) {
        // Define and Initialize Devices
        frontLeft  = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft   = hwMap.get(DcMotor.class, "backLeft");
        backRight  = hwMap.get(DcMotor.class, "backRight");
        rotateLeft = hwMap.get(DcMotor.class, "rotateLeft");
        rotateRight = hwMap.get(DcMotor.class, "rotateRight");



        // Set Direction
        frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rotateLeft.setDirection(DcMotor.Direction.REVERSE);
        rotateRight.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        rotateLeft.setPower(0);
        rotateRight.setPower(0);
        // RUN TO POSITION
        // Set all motors to run with encoders if applicable.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotateLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotateRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
}

