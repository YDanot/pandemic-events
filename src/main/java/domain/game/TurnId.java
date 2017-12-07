
package domain.game;

import java.util.UUID;

public class TurnId {

	private final UUID id = UUID.randomUUID();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TurnId))
			return false;

		TurnId turnId = (TurnId) o;

		return id.equals(turnId.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
