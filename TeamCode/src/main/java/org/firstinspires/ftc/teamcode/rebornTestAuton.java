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
        lift(3.2, -1, 350);//400 if starting from bottom 250 starting otherwise
        lift(1, 2, 2000);
//        lift(0.08, 1, 2000);
        sleep(1500);
        robot.clawServo.setPosition(.15);

    }
}
