package hellojpa;

import jdk.jfr.Name;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    private Long id;
    @Name("name") // DB Member Table에서 해당 컬럼(name) 지정
    private String name;

    public Member(){ // 기본 생성자
    }


    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getter, Setter ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
