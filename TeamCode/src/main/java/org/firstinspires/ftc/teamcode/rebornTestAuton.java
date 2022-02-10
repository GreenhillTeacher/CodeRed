package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Single Operation", group="reborn")
//@Disabled
public class rebornTestAuton extends rebornDriving{
    @Override
    public void runOpMode (){
        robot.init(hardwareMap);

        waitForStart();
        robot.clawServo.setPosition(.15);


        //Parameters
        char clawTarget = 't'; //l = low; m = mid; h = high; t = until no reading (up)

        //the program

        double lowPosition = 85;//target positions, in mm
        double midPosition = 100;
        double highPosition = 250;

        double precisePower = .03;//powers of the motor in different modes
        double roughPower = .05;
        double correctionSpeed = .03;

        int waitTime = 100;
        int distanceCutoff = 2000;

        double targetPosition;

        switch (clawTarget){//sets the target based on the user's input
            case 'l':
            case 'b':
                targetPosition = lowPosition;
                break;
            case 'm':
                targetPosition = midPosition;
                break;
            case 't':
            case 'h':
                targetPosition = highPosition;
                break;
            default:
                targetPosition = 3000;//this is a test number. Should read very high if not pointing at anything.
        }

        double currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);

        motorStop();

        while (currentPosition - 500 > targetPosition){//if the current position is 50 cm more than the target position, it moves very speedily to get there
            robot.rotateLeft.setPower(roughPower);//it should only be that large if the claw is in the upward position.
            robot.rotateRight.setPower(roughPower);

            currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);

            telemetry.addData("Mode:", "Rough Movement");
            telemetry.addData("Target Position:", targetPosition);
            telemetry.addData("Current Position:", currentPosition);
            telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
            telemetry.update();
        }

        motorStop();

        sleep(waitTime);
        telemetry.addData("Mode:", "Waiting for Fine");
        telemetry.addData("Target Position:", targetPosition);
        telemetry.addData("Current Position:", currentPosition);
        telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
        telemetry.update();
        sleep(waitTime);
        currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);


        while (currentPosition > targetPosition){//once it gets closer, it slows down
            robot.rotateLeft.setPower(precisePower);
            robot.rotateRight.setPower(precisePower);

            currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);

            telemetry.addData("Mode:", "Fine Movement");
            telemetry.addData("Target Position:", targetPosition);
            telemetry.addData("Current Position:", currentPosition);
            telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
            telemetry.update();
        }
        motorStop();
        sleep(waitTime);
        telemetry.addData("Mode:", "Waiting for Correct");
        telemetry.addData("Target Position:", targetPosition);
        telemetry.addData("Current Position:", currentPosition);
        telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
        telemetry.update();
        sleep(waitTime);
        currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);

        int correctionError = 10;
        if (clawTarget == 't'||clawTarget=='h'){
            correctionError = 0;
        }

        while (currentPosition + correctionError < targetPosition){//once it is close, it goes back up until it is within 1 cm.
            robot.rotateLeft.setPower(-roughPower);
            robot.rotateRight.setPower(-roughPower);

            currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);

            telemetry.addData("Mode:", "Correction Movement");
            telemetry.addData("Target Position:", targetPosition);
            telemetry.addData("Current Position:", currentPosition);
            telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
            telemetry.update();

            if((int)currentPosition > distanceCutoff){//if it gets super high, it will (hopefully) stop.
                break;
            }
        }
        motorStop();
        sleep(waitTime);
        currentPosition = robot.clawDist.getDistance(DistanceUnit.MM);
        telemetry.addData("Mode:", "Done");
        telemetry.addData("Target Position:", targetPosition);
        telemetry.addData("Current Position:", currentPosition);
        telemetry.addData("Distance Left:", Math.abs(targetPosition-currentPosition));
        telemetry.update();
        sleep(waitTime);
        sleep(2000);

    }
}
