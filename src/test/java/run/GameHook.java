package run;

import cucumber.api.java.Before;
import infra.World;

public class GameHook {

	@Before
	public void create() {
		World.create();
	}
}
