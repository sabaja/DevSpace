package a1com.mvc.spittr.pojo;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Spittle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6612385386912566381L;
	private static Long id = 0L;
	private String message;

	// Instant instant = Instant.now(); // get The current time in instant
	// object
	// Timestamp t=java.sql.Timestamp.from(instant); // Convert instant to
	// Timestamp
	// Instant anotherInstant=t.toInstant(); // Convert Timestamp to Instant
	private Instant time; // timestamp
	private Long longitude;
	private Long latitude;

	public Spittle(){
		this(null, Instant.now(), null, null);
	}

	public Spittle(String message) {
		this(message, Instant.now(), null, null);
	}

	public Spittle(String message, Instant date) {
		this(message, date, null, null);
	}

	public Spittle(String message, Instant time, Long longitude, Long latitude) {
		super();
		Spittle.id += 1L;
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Instant getTime() {
		return time;
	}

	public Long getLongitude() {
		return longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	/**
	 * he only thing to note is that you’re using Apache Commons Lang for easy
	 * implementa- tion of the equals() and hashCode() methods. Aside from the
	 * general utility value of those methods, they’ll be valuable in writing a
	 * test for the controller handler method. While we’re on the subject of
	 * testing, let’s go ahead and write a test for the new controller method.
	 * The following listing uses Spring’s MockMvc to assert the behavior you
	 * want in the new handler method.
	 */

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}

	/**
	 * Parameters: 1 this object 2 the other object excludeFields array of field
	 * names to exclude from testing Returns: true if the two Objects have
	 * tested equals. See Also: EqualsExclude
	 */

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}

	public static void setId(Long id) {
		Spittle.id = id;
	}

}
