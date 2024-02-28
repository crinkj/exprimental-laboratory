package laboratory.experiment.crew.domain

import jakarta.persistence.*
import org.hibernate.annotations.Fetch

@Entity
@Table(name = "crew_members")
class CrewMember(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        @ManyToOne
        @JoinColumn(name = "crewId")
        val crew: Crew
)