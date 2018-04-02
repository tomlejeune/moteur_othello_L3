package engine;

import java.awt.Color;
import java.util.UUID;

public abstract class Bot extends Player {

    Bot(UUID id, String nickname) {
        super(id, nickname);
    }
}