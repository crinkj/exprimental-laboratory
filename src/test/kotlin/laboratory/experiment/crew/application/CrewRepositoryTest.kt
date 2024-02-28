package laboratory.experiment.crew.application

import jakarta.persistence.EntityManager
import laboratory.experiment.crew.domain.Crew
import laboratory.experiment.crew.domain.CrewMember
import laboratory.experiment.crew.infra.CrewMemberRepository
import laboratory.experiment.crew.infra.CrewRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class CrewRepositoryTest @Autowired constructor(
        private val crewRepository: CrewRepository,
        private val crewMemberRepository: CrewMemberRepository,
        private val entityManager: EntityManager
) {

    @BeforeEach
    fun setup() {

        val crew = Crew(
                id = 1,
                name = "crew_1"

        ).apply {
            val members: MutableList<CrewMember> = getCrewMembers(crew = this)
            this.members.addAll(members.toMutableSet())
        }

        crewRepository.save(crew)


        // 엔티티 매니저로 clear해줘야 독립적인 transactional에서 영속성 컨텍스를 제거하고 db를 조회한다.
        entityManager.clear()
    }

    @AfterEach
    fun tearDown() {
        crewMemberRepository.deleteAllInBatch()
        crewRepository.deleteAllInBatch()
    }

    @Test
    @DisplayName("크루를 조회할 경우 N+1이 발생하는지 테스트한다.")
    @Transactional
    fun test_N_PLUS_ONE() {
        println("================== START ====================")

        val crews = crewRepository.findAllBy()
        crews.forEach {
            it.members.first().name
        }

        println("================== END ====================")
    }

    private fun getCrewMembers(crew: Crew): MutableList<CrewMember> {
        val members = mutableListOf<CrewMember>()

        for (i: Int in 1..5) {
            members.add(
                    CrewMember(
                            id = i.toLong(),
                            name = "test${i}",
                            crew = crew
                    )
            )
        }
        return members
    }


}