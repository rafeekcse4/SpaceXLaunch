package spacexlaunch.com.spacexlaunch.ApiMapper;

import java.util.List;

public class Flight {

	private float flight_number;
	private String mission_name;
	private List<Object> mission_id;
	private boolean upcoming;
	private String launch_year;
	private float launch_date_unix;
	private String launch_date_utc;
	private String launch_date_local;
	private boolean is_tentative;
	private String tentative_max_precision;
	private Rocket rocket;
	private List<Object> ships;
	private Telemetry telemetry;
	private Reuse reuse;
	private Launch_site launch_site;
	private boolean launch_success;
	private Links links;
	private String details;
	private String static_fire_date_utc;
	private float static_fire_date_unix;
	public float getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(float flight_number) {
		this.flight_number = flight_number;
	}
	public String getMission_name() {
		return mission_name;
	}
	public void setMission_name(String mission_name) {
		this.mission_name = mission_name;
	}
	public List<Object> getMission_id() {
		return mission_id;
	}
	public void setMission_id(List<Object> mission_id) {
		this.mission_id = mission_id;
	}
	public boolean isUpcoming() {
		return upcoming;
	}
	public void setUpcoming(boolean upcoming) {
		this.upcoming = upcoming;
	}
	public String getLaunch_year() {
		return launch_year;
	}
	public void setLaunch_year(String launch_year) {
		this.launch_year = launch_year;
	}
	public float getLaunch_date_unix() {
		return launch_date_unix;
	}
	public void setLaunch_date_unix(float launch_date_unix) {
		this.launch_date_unix = launch_date_unix;
	}
	public String getLaunch_date_utc() {
		return launch_date_utc;
	}
	public void setLaunch_date_utc(String launch_date_utc) {
		this.launch_date_utc = launch_date_utc;
	}
	public String getLaunch_date_local() {
		return launch_date_local;
	}
	public void setLaunch_date_local(String launch_date_local) {
		this.launch_date_local = launch_date_local;
	}
	public boolean isIs_tentative() {
		return is_tentative;
	}
	public void setIs_tentative(boolean is_tentative) {
		this.is_tentative = is_tentative;
	}
	public String getTentative_max_precision() {
		return tentative_max_precision;
	}
	public void setTentative_max_precision(String tentative_max_precision) {
		this.tentative_max_precision = tentative_max_precision;
	}
	
	public List<Object> getShips() {
		return ships;
	}
	public void setShips(List<Object> ships) {
		this.ships = ships;
	}
	
	public boolean isLaunch_success() {
		return launch_success;
	}
	public void setLaunch_success(boolean launch_success) {
		this.launch_success = launch_success;
	}
	
	public Telemetry getTelemetry() {
		return telemetry;
	}
	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}
	public Reuse getReuse() {
		return reuse;
	}
	public void setReuse(Reuse reuse) {
		this.reuse = reuse;
	}
	public Launch_site getLaunch_site() {
		return launch_site;
	}
	public void setLaunch_site(Launch_site launch_site) {
		this.launch_site = launch_site;
	}
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getStatic_fire_date_utc() {
		return static_fire_date_utc;
	}
	public void setStatic_fire_date_utc(String static_fire_date_utc) {
		this.static_fire_date_utc = static_fire_date_utc;
	}
	public float getStatic_fire_date_unix() {
		return static_fire_date_unix;
	}
	public void setStatic_fire_date_unix(float static_fire_date_unix) {
		this.static_fire_date_unix = static_fire_date_unix;
	}
	public Rocket getRocket() {
		return rocket;
	}
	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}
	
	

}
