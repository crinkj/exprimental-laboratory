package laboratory.experiment.crew.infra

import laboratory.experiment.crew.domain.Crew
import laboratory.experiment.crew.domain.CrewMember
import org.springframework.data.jpa.repository.JpaRepository

interface CrewMemberRepository : JpaRepository<CrewMember, Long> {
}