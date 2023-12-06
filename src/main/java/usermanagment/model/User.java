package usermanagment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;
 
    @Column(name="name")
    protected String name;
 
    @Column(name="email")
    protected String email;
 
    @Column(name="contact")
    protected String contact;
 
    public User() {
    }
 
    public User(String name, String email, String contact) {
        super();
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public User(int id, String name, String email, String contact) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }
    // getter/setter methods    

	
	

    public  int getId() {
		return id;
	}
    public  void setId(int id) {
		this.id = id;
	}
    public  String getName() {
		return name;
	}
    public  void setName(String name) {
		this.name = name;
	}
    public  String getEmail() {
		return email;
	}
    public  void setEmail(String email) {
		this.email = email;
	}
    public  String getContact() {
		return contact;
	}
    public  void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
	}
	

}
