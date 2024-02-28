package laboratory.experiment.crew.domain

import jakarta.persistence.*

@Entity
@Table(name = "crews")
class Crew(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String
) {

    @OneToMany(mappedBy = "crew", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val members: MutableSet<CrewMember> = mutableSetOf()

}