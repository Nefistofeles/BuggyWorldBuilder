package main;

import java.util.HashMap;
import java.util.Map;

public abstract class StateManager {

	public static enum StateEnum{
		GameState,
		MenuState,
		WorldCreator
	}
	private static StateManager currentState ;
	private static Map<StateEnum, StateManager> states ;
	
	public abstract void init();
	public abstract void update();
	public abstract void clear();
	
	
	public static StateManager getCurrentState() {
		return currentState;
	}
	public static void addState(StateEnum enumState , StateManager state) {
		if(states == null) {
			states = new HashMap<StateEnum, StateManager>();
		}
		states.put(enumState, state);
	}
	public static void setCurrentState(StateEnum state) {
		if(StateManager.currentState != null) {
			StateManager.currentState.clear();
		}
		try {
			StateManager.currentState = states.get(state);
			currentState.init();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("state is not found");
			System.exit(-1);
		}

	}
	
	
	
	
}
