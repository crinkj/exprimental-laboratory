package laboratory.experiment.crew.infra

import laboratory.experiment.crew.domain.Crew
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CrewRepository : JpaRepository<Crew, Long> {

    @EntityGraph(attributePaths = ["members"], type = EntityGraph.EntityGraphType.FETCH)
    fun findAllBy(): List<Crew>

}