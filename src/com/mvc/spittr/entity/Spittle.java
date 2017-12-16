package com.mvc.spittr.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Formula;

/**
 * SECONDARY TABLE
 * http://www.concretepage.com/hibernate/secondarytables_hibernate_annotation
 * 
 * @author sabaja
 *
 */

@Entity
@Table(name = "SPITTLE", schema = "HIBERNATE")
//@SecondaryTable(name = "SPITTER_SPITTLE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "SPITTER_ID", referencedColumnName="SPITTER_ID"))
public class Spittle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6612385386912566381L;

	@Id
	@Column(name = "SPITTLE_ID", nullable = false, unique = true)

	// GenerationType.AUTO
	// This is the default strategy and is portable across different databases.
	// Hibernate chooses the appropriate ID based on the database.
	// schema resource at runtime.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	
	@Column(name = "SPITTLE_MESSAGE", nullable = true)
	private String message;

	// Automatic conversion type
	@Column(name = "DATE_TIME", nullable = false)
	private Instant time;
	@Column(name = "LONGITUDE", nullable = true)
	private Long longitude;
	@Column(name = "LATITUDE", nullable = true)
	private Long latitude;

	public Spittle() {
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
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return EqualsBuilder.reflectionEquals(this, that, "id", "time", "longitude", "latitude");
	}

	/**
	 * Parameters: 1 this object 2 the other object excludeFields array of field
	 * names to exclude from testing Returns: true if the two Objects have
	 * tested equals. See Also: EqualsExclude
	 */

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time","longitude", "latitude");
	}

	@Override
	public String toString() {
		return "Spittle [id=" + id + ", message=" + message + ", time=" + time + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}

//	public Spitter getSpitter() {
//		return this.spitter;
//	}
//
//	public void setSpitter(Spitter spitter) {
//		this.spitter = spitter;
//	}


}
