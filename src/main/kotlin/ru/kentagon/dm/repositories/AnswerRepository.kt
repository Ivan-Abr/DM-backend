package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Answer
import java.util.UUID

@Repository
interface AnswerRepository : JpaRepository<Answer, UUID> {
    @Query(
        "SELECT  avg(a.mark.value), l.name, org.name, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.id = m.id" +
                "   LEFT JOIN Question AS q ON m.question.id = q.id" +
                "   LEFT JOIN Layer AS l ON q.layer.id = l.id" +
                "   LEFT JOIN Organization as org On a.organization.id = org.id" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.id = mlst.id" +
                "   WHERE l.id = ?1 and org.id = ?2" +
                "   GROUP BY l.name, org.name, mlst.year")
    fun getAllByLayerOrg(layerId: UUID, orgId: UUID): Any

    @Query(
        "SELECT  avg(a.mark.value), f.shortname, org.name, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.id = m.id" +
                "   LEFT JOIN Question AS q ON m.question.id = q.id" +
                "   LEFT JOIN Factor AS f ON q.factor.id = f.id" +
                "   LEFT JOIN Organization as org On a.organization.id = org.id" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.id = mlst.id" +
                "   WHERE f.id = ?1 and org.id = ?2" +
                "   GROUP BY f.shortname, org.name,mlst.year")
    fun getAllByFactorOrg(factorId: UUID, orgId: UUID): Any

    @Query(
        "SELECT  avg(a.mark.value), l.name, f.shortname, org.name, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.id = m.id" +
                "   LEFT JOIN Question AS q ON m.question.id = q.id" +
                "   LEFT JOIN Factor AS f ON q.factor.id = f.id" +
                "   LEFT JOIN Layer AS l ON q.layer.id = l.id" +
                "   LEFT JOIN Organization as org On a.organization.id = org.id" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.id = mlst.id" +
                "   WHERE org.id = ?1" +
                "   GROUP BY l.name, org.name, f.shortname, mlst.year")
    fun getAllByOrgId(orgId: UUID): List<Any>
}
