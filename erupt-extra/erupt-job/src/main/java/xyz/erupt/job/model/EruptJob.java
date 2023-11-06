package xyz.erupt.job.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.EruptI18n;
import xyz.erupt.annotation.constant.AnnotationConst;
import xyz.erupt.annotation.sub_erupt.Drill;
import xyz.erupt.annotation.sub_erupt.Link;
import xyz.erupt.annotation.sub_erupt.RowOperation;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.annotation.sub_field.sub_edit.TagsType;
import xyz.erupt.job.model.data_proxy.EruptJobDataProxy;
import xyz.erupt.job.service.ChoiceFetchEruptJobHandler;
import xyz.erupt.jpa.model.MetaModelUpdateVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author YuePeng
 * date 2019-12-26
 */
@EruptI18n
@Erupt(
        name = "任务维护",
        dataProxy = EruptJobDataProxy.class,
        drills = @Drill(title = "日志", icon = "fa fa-sliders", link = @Link(linkErupt = EruptJobLog.class, joinColumn = "eruptJob.id")),
        rowOperation = @RowOperation(code = "action", icon = "fa fa-play", title = "执行一次任务", operationHandler = EruptJobDataProxy.class)
)
@Entity
@Table(name = "e_job", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Component
@Getter
@Setter
public class EruptJob extends MetaModelUpdateVo {

    @Column(length = AnnotationConst.CODE_LENGTH)
    @EruptField(
            views = @View(title = "코딩", width = "100px")
    )
    private String code;

    @EruptField(
            views = @View(title = "명령 이름"),
            edit = @Edit(title = "명령 이름", notNull = true, search = @Search(vague = true))
    )
    private String name;

    @EruptField(
<<<<<<< Updated upstream
            views = @View(title = "Cron表达式"),
            edit = @Edit(title = "Cron表达式", notNull = true)
=======
            views = @View(title = "Cron표현식", width = "150px"),
            edit = @Edit(title = "Cron표현식", notNull = true)
>>>>>>> Stashed changes
    )
    private String cron;

    @EruptField(
            views = @View(title = "JOB 처리 클래스"),
            edit = @Edit(title = "JOB 처리 클래스", desc = "EruptJobHandler 인터페이스를 구현하기만 하면 됩니다.",
                    choiceType = @ChoiceType(fetchHandler = ChoiceFetchEruptJobHandler.class)
                    , notNull = true, search = @Search, type = EditType.CHOICE)
    )
    private String handler;

    @EruptField(
            views = @View(title = "작업 상태"),
            edit = @Edit(title = "작업 상태", boolType = @BoolType(
                    trueText = "사용", falseText = "금지"
            ), notNull = true, search = @Search)
    )
    private Boolean status;

    @Column(length = AnnotationConst.REMARK_LENGTH)
    @EruptField(
            views = @View(title = "실패 알림 이메일"),
            edit = @Edit(title = "실패 알림 이메일", desc = "이 기능을 사용하려면 보내는 이메일 주소를 구성해야 합니다.", type = EditType.TAGS, tagsType = @TagsType)
    )
    private String notifyEmails;

    @Column(length = AnnotationConst.REMARK_LENGTH)
    @EruptField(
            views = @View(title = "작업 매개변수"),
            edit = @Edit(title = "작업 매개변수", type = EditType.CODE_EDITOR)
    )
    private String handlerParam;

    @Column(length = AnnotationConst.REMARK_LENGTH)
    @EruptField(
            views = @View(title = "설명"),
            edit = @Edit(title = "설명")
    )
    private String remark;


}
