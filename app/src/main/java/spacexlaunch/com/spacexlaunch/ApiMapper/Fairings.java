package spacexlaunch.com.spacexlaunch.ApiMapper;

public class Fairings {

	private boolean reused;
	private boolean recovery_attempt;
	private boolean recovered;
	private String ship;
	public boolean isReused() {
		return reused;
	}
	public void setReused(boolean reused) {
		this.reused = reused;
	}
	public boolean isRecovery_attempt() {
		return recovery_attempt;
	}
	public void setRecovery_attempt(boolean recovery_attempt) {
		this.recovery_attempt = recovery_attempt;
	}
	public boolean isRecovered() {
		return recovered;
	}
	public void setRecovered(boolean recovered) {
		this.recovered = recovered;
	}
	public String getShip() {
		return ship;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	
	

}
