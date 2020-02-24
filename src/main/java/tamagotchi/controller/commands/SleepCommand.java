package tamagotchi.controller.commands;

import javafx.event.ActionEvent;
import tamagotchi.PropertiesAccessPoint;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.controller.timer.GameTimer;
import tamagotchi.controller.timer.GameTimers;
import tamagotchi.controller.timer.tasks.UpdateTimerTask;
import tamagotchi.model.pet.Occupation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;

public class SleepCommand extends Command {
    public SleepCommand() {
        super();
    }

    private static void continueToSleep(ActionEvent actionEvent) {
        getMotionAnimation().stayAnimation(
                actionEvent1 -> getMotionAnimation().stayAnimation(actionEvent2 -> {
                    if (ModelContainer.getPetInstance().getCurrentOccupation().equals(Occupation.SLEEPING)) {
                        continueToSleep(actionEvent2);
                    } else {
                        GameTimers.getInstance().getPetSleepUpdateGameTimer().stopTimer();
                        Controller.controlCommandExecution(actionEvent2);
                    }
                }));
    }

    @Override
    public void execute() {
        ViewContainer.getStatusLabel().setText(
                ModelContainer.getPetInstance().getCurrentOccupation().getStatusMessage());

        GameTimers.getInstance().getPetSleepUpdateGameTimer().startTimer(
                Long.parseLong(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickSpan")),
                Long.parseLong(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickSpan"))
        );
        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                .getSpriteAnimationSetting("sleep"));
        getMotionAnimation().setTransitionWithTexture(petTexture);
        getMotionAnimation().stayAnimation(actionEvent
                -> getMotionAnimation().stayAnimation(SleepCommand::continueToSleep));
    }
}
