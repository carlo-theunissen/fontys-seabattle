package game;

import java.util.Observer;

public interface IUserInput {

	/**
	 * 
	 * @param observer
	 */
	void setOnMouseMove(Observer observer);

	/**
	 * 
	 * @param observer
	 */
	void setOnLeftMouseButton(Observer observer);

}