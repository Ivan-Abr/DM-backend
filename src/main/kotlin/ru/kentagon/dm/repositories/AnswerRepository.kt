package ru.kentagon.dm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kentagon.dm.models.Answer
import java.util.UUID

@Repository
interface AnswerRepository : JpaRepository<Answer, UUID> {
    @Query(
        "SELECT  avg(a.mark.markValue), l.layerName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Layer AS l ON q.layer.layerId = l.layerId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE l.layerId = ?1 and org.orgId = ?2" +
                "   GROUP BY l.layerName, org.orgName, mlst.year")
    fun getAllByLayerOrg(layerId: Long, orgId: Long): Any

    @Query(
        "SELECT  avg(a.mark.markValue), f.factorShortName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Factor AS f ON q.factor.factorId = f.factorId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE f.factorId = ?1 and org.orgId = ?2" +
                "   GROUP BY f.factorShortName, org.orgName,mlst.year")
    fun getAllByFactorOrg(factorId: Long, orgId: Long): Any

    @Query(
        "SELECT  avg(a.mark.markValue), l.layerName, f.factorShortName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.mark.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Factor AS f ON q.factor.factorId = f.factorId" +
                "   LEFT JOIN Layer AS l ON q.layer.layerId = l.layerId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE org.orgId = ?1" +
                "   GROUP BY l.layerName, org.orgName, f.factorShortName, mlst.year")
    fun getAllByOrgId(orgId: Long): List<Any>
}
