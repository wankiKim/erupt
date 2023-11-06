package xyz.erupt.job.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.EruptI18n;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author YuePeng
 * date 2019-12-26
 */
@EruptI18n
@Erupt(
        orderBy = "startTime desc",
        name = "임무 기록",
        power = @Power(export = true, add = false, edit = false, viewDetails = false)
)
@Entity
@Table(name = "e_job_log")
@Getter
@Setter
public class EruptJobLog extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "job_id")
    @EruptField(
            views = @View(title = "임무 이름", column = "name"),
            edit = @Edit(title = "임무 이름", show = false, search = @Search, type = EditType.REFERENCE_TREE)
    )
    private EruptJob eruptJob;

    @EruptField(
            views = @View(title = "작업 매개변수")
    )
    private String handlerParam;

    @EruptField(
            views = @View(title = "작업 상태"),
            edit = @Edit(title = "작업 상태", boolType = @BoolType(trueText = "성공", falseText = "실패"), search = @Search)
    )
    private Boolean status;

    @EruptField(
            views = @View(title = "시작 시간", type = ViewType.DATE_TIME)
    )
    private Date startTime;

    @EruptField(
            views = @View(title = "종료 시간",type = ViewType.DATE_TIME)
    )
    private Date endTime;

    @Column(length = 2000)
    @EruptField(
            views = @View(title = "결과"),
            edit = @Edit(title = "결과")
    )
    private String resultInfo;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @EruptField(
            views = @View(title = "에러 메시지", type = ViewType.HTML),
            edit = @Edit(title = "에러 메시지")
    )
    private String errorInfo;

}
