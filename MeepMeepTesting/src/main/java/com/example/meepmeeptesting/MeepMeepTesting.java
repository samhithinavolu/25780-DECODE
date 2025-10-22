package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity blueGoalBot1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setDimensions(16,16)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        RoadRunnerBotEntity blueGoalBot2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setDimensions(16,16)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        blueGoalBot1.runAction(blueGoalBot1.getDrive().actionBuilder(new Pose2d(-64, -34, Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-48, -16), Math.toRadians(240))
                .waitSeconds(0.25)
                .strafeToLinearHeading(new Vector2d(-12, -30), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-12, -48), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-34, -34), Math.toRadians(220))
                .build());

        blueGoalBot2.runAction(blueGoalBot2.getDrive().actionBuilder(new Pose2d(-40, -52, Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-34, -25), Math.toRadians(230))
                .waitSeconds(0.25)
                .strafeToLinearHeading(new Vector2d(-12, -30), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-12, -48), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(-34, -34), Math.toRadians(220))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueGoalBot1)
                .addEntity(blueGoalBot2)
                .start();
    }
}