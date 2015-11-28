package introsde.assignment.soap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import introsde.assignment.soap.dao.LifeCoachDao;



/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name="Measure")
@NamedQuery(name="Measure.findAll", query="SELECT m FROM Measure m")
@XmlRootElement
public class Measure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_measuredef")
	@TableGenerator(name="sqlite_measuredef", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="mid")
	@Column(name="mid")
	private Long mid;

	@Temporal(TemporalType.DATE) 
	@Column(name="dateRegistered")
	Date dateRegistered;

	@Column(name="measureName")
	private String measureName;

	@Column(name="measureType")
	private String measureType;
	
	@Column(name="measureValueType")
	private String measureValueType;

	@ManyToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
	private Person person;
	


	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public String getMeasureValueType() {
		return measureValueType;
	}

	public void setMeasureValueType(String measureValueType) {
		this.measureValueType = measureValueType;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
	

	// database operations
	public static Measure getMeasureDefinitionById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Measure p = em.find(Measure.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Measure> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Measure> list = em.createNamedQuery("Measure.findAll", Measure.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Measure saveMeasureDefinition(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Measure updateMeasureDefinition(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasureDefinition(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}