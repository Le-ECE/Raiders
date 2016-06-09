package com.mygdx.game;

/**
 * The Save class acts as a data class that temporarily stores the user's data,
 * such as their username, the difficulty and level they are currently on, as
 * well as the time they currently have (in seconds). The class contains
 * various getters and setters that allow for the easy manipulation of data.
 * 
 * @author David Le
 * @version 4.0 07.06.2016
 *
 */
public class Save {
	private String name;
	private int currentDifficulty;
	private int timeSeconds;

	/**
	 * The Save constructor is used to take in the username, the current stage,
	 * and the time the user has as parameters, and store them in the class 
	 * through variables.
	 * 
	 * @param newName String representing username
	 * @param newDifficulty int representing current stage
	 * @param newTimeSeconds int representing current time
	 */
	public Save(String newName, int newDifficulty, int newTimeSeconds) {
		this.name = newName;
		this.currentDifficulty = newDifficulty;
		this.timeSeconds = newTimeSeconds;
	}

	/**
	 * Get method for username.
	 * @return name String representing username
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get method for current stage
	 * @return currentDifficulty int representing current stage
	 */
	public int getDifficulty() {
		return this.currentDifficulty;
	}

	/**
	 * Get method for current time
	 * @return timeSeconds int representing amount of time
	 */
	public int getTimeSeconds() {
		return this.timeSeconds;
	}

	/**
	 * Set method for username
	 * @param newName String representing new username
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Set method for stage
	 * @param newDifficulty int representing new stage
	 */
	public void setDifficulty(int newDifficulty) {
		this.currentDifficulty = newDifficulty;
	}

	/**
	 * Set method for time
	 * @param newTimeSeconds int representing new time
	 */
	public void setTimeSeconds(int newTimeSeconds) {
		this.timeSeconds = newTimeSeconds;
	}

	/**
	 * Get method for formatted time in hours:minutes:seconds
	 * @return String formatted String representing formatted time in hh:mm:ss format
	 */
	public String getTotalTime() {
		return String.format("%02d:%02d:%02d", this.timeSeconds / 3600, (this.timeSeconds % 3600) / 60,
				this.timeSeconds % 60);
	}

}