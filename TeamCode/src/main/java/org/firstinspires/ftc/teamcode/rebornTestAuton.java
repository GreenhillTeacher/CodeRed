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

        //Parameters

        double distance = 20.0;
        boolean relativeMove = false;

        //==============================

        double reversePercent = .8;
        double power = .1;


        double startingDist = robot.backDist.getDistance(DistanceUnit.CM);
        double goalDist = distance;
        if(relativeMove) {
            goalDist+=startingDist;
        }

        double currentDist = robot.backDist.getDistance(DistanceUnit.CM);

        while (currentDist<=goalDist){
            robot.frontLeft.setPower(power);
            robot.frontRight.setPower(power);
            robot.backLeft.setPower(power);
            robot.backRight.setPower(power);
            currentDist = robot.backDist.getDistance(DistanceUnit.CM);
            telemetry.addData("Current Dist", currentDist);
            telemetry.update();
        }
        motorStop();
        sleep(1000);
        currentDist = robot.backDist.getDistance(DistanceUnit.CM);

        if (currentDist> goalDist+2.0){
            robot.frontLeft.setPower(-power );//this isn't moving backwards for some reason we'll figure out later. XOXO Quyen, Feb 7
            robot.frontRight.setPower(-power );
            robot.backLeft.setPower(-power);
            robot.backRight.setPower(-power);
            currentDist = robot.backDist.getDistance(DistanceUnit.CM);
            telemetry.addData("Current Dist (reverse)", currentDist);
            telemetry.update();
        }
        motorStop();
        sleep(10000);



    }
}
