package knolif.galaga.level.levelgen;

import knolif.galaga.Level;
import knolif.galaga.level.script.RandomScript;

public class LevelGen {
	Level level;
	
	public LevelGen(Level l) {
		level = l;
	}

	public void buildLevel() {
		//TODO: Build the background array / image
	}
	public void buildScripts() {
		//TODO: Build the list of scripts that controls the game
		level.scripts.add(new RandomScript(level, level.screen, 0, level.length));
	}
}
