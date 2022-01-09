package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Reborn Test", group="reborn")

//THIS DOESNT WORK RIGHT NOW
//AFTER START< IT GOES FORWARD INDEFINITELY. PROBABLY BECAUSE WE DONT HAVE ENCODERS

public class rebornTestAuton extends rebornDriving{
    @Override
    public void runOpMode (){
        robot.init(hardwareMap);

        waitForStart();

        move(1, 'f', 5);
        sleep(1000);
        move(1, 'b', 5);
        sleep(1000);
    }
}
