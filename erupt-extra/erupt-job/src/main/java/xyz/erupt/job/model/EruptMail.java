package xyz.erupt.job.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.EruptI18n;
import xyz.erupt.annotation.config.EruptSmartSkipSerialize;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.constant.RegexConst;
import xyz.erupt.job.model.data_proxy.EruptMailDataProxy;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author YuePeng
 * date 2019-12-26
 */
@EruptI18n
@Erupt(
        name = "发送邮件",
        dataProxy = EruptMailDataProxy.class,
        power = @Power(edit = false),
        orderBy = "id desc"
)
@Entity
@Getter
@Setter
@Table(name = "e_job_mail")
@Component
public class EruptMail extends BaseModel {

    @EruptField(
            views = @View(title = "받는사람"),
            edit = @Edit(title = "받는사람", notNull = true, search = @Search(vague = true),
                    inputType = @InputType(fullSpan = true, regex = RegexConst.EMAIL_REGEX))
    )
    private String recipient;

    @EruptField(
            views = @View(title = "참조"),
            edit = @Edit(title = "참조", type = EditType.TAGS)
    )
    private String cc;

    @EruptField(
            views = @View(title = "제목"),
            edit = @Edit(title = "제목", notNull = true, search = @Search(vague = true), inputType = @InputType(fullSpan = true))
    )
    private String subject;

    @EruptField(
            views = @View(title = "상태"),
            edit = @Edit(title = "상태", boolType = @BoolType(trueText = "성공", falseText = "실패"), show = false)
    )
    private Boolean status;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @EruptField(
            views = @View(title = "콘텐츠"),
            edit = @Edit(title = "콘텐츠", notNull = true, type = EditType.HTML_EDITOR)
    )
    private String content;

    @Column(length = 5000)
    private String errorInfo;

    @EruptField(
            views = @View(title = "발송시간")
    )
    private Date createTime;

    @EruptField(
            views = @View(title = "발신인")
    )
    @EruptSmartSkipSerialize
    private String createBy;


}
