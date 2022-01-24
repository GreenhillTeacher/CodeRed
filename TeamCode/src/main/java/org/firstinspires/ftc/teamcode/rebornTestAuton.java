package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Reborn Test", group="reborn")

public class rebornTestAuton extends rebornDriving{
    @Override
    public void runOpMode (){
        robot.init(hardwareMap);

        waitForStart();

        int testDist = 90;

        motorStop();

        rotate(.6, 'l', testDist);
        sleep (1000);
        rotate(.6, 'r', testDist);
        sleep(1000);
        rotate(.6, 'l', testDist);
    }
}
