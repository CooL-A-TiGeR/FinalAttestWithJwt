package my.app.finalAtt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "commons")
public class Commons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private String commonsTitle;
    private double commonsPrice;
    private double commonsVolume;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Commons(String commonsTitle, double commonsPrice, double commonsVolume, Category category) {
        this.commonsTitle = commonsTitle;
        this.commonsPrice = commonsPrice;
        this.commonsVolume = commonsVolume;
        this.category = category;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Commons commons = (Commons) o;
//        return id != null && Objects.equals(id, commons.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
