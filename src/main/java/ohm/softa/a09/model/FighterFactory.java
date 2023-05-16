package ohm.softa.a09.model;

import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.resource.ResourceLoader;
import ohm.softa.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final FxImageLoaderAdapter imageLoader;

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		imageLoader = new FxImageLoaderAdapter();
	}

	/**
	 * Create a random Fighter
	 *
	 * @return a new Fighter instance
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 */
	public Fighter createFighter() {
		var name = nameGenerator.generateName();

		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(name, getImage("fighter/awing.jpg"));
			case 1:
				return new XWing(name, getImage("fighter/xwing.jpg"));
			case 2:
				return new YWing(name, getImage("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, getImage("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, getImage("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, getImage("fighter/tieinterceptor.jpg"));
		}
	}

	// Flyweight pattern
	private Map<String, Image> cache = new HashMap<>();

	private Image getImage(String path) {
		// adds image if it isn't already in the cache
		cache.computeIfAbsent(path,
			image -> imageLoader.loadImage(path));

		return cache.get(path);
	}
}
