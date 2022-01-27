package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Single Operation", group="reborn")
//@Disabled
public class rebornTestAuton extends rebornDriving{
    @Override
    public void runOpMode (){
        robot.init(hardwareMap);

        waitForStart();

        int testDist = 2;

        motorStop();

        lift(2, 1000);
        robot.clawServo.setPosition(.15);

    }
}
