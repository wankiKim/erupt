package xyz.erupt.upms.model;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.EruptI18n;
import xyz.erupt.annotation.constant.AnnotationConst;
import xyz.erupt.annotation.sub_erupt.Tree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.Readonly;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.CodeEditorType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.core.constant.MenuStatus;
import xyz.erupt.core.constant.MenuTypeEnum;
import xyz.erupt.core.module.MetaMenu;
import xyz.erupt.jpa.model.MetaModel;
import xyz.erupt.upms.service.EruptMenuService;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author YuePeng
 * date 2018-11-22.
 */
@Entity
@Table(name = "e_upms_menu", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Erupt(
        name = "메뉴 관리",
        orderBy = "EruptMenu.sort asc",
        tree = @Tree(pid = "parentMenu.id", expandLevel = 5),
        dataProxy = EruptMenuService.class
)
@EruptI18n
@Getter
@Setter
public class EruptMenu extends MetaModel {

    public static final String CODE = "code";

    @EruptField(
            views = @View(title = "이름"),
            edit = @Edit(
                    title = "이름",
                    notNull = true
            )
    )
    private String name;

    @EruptField(
            edit = @Edit(
                    notNull = true,
                    title = "상태",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(fetchHandler = MenuStatus.ChoiceFetch.class)
            )
    )
    private Integer status;

    @ManyToOne
    @EruptField(
            edit = @Edit(
                    title = "이전 메뉴",
                    type = EditType.REFERENCE_TREE,
                    referenceTreeType = @ReferenceTreeType(pid = "parentMenu.id", expandLevel = 3)
            )
    )
    private EruptMenu parentMenu;

    @EruptField(
            edit = @Edit(
                    title = "메뉴 종류",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(fetchHandler = MenuTypeEnum.ChoiceFetch.class)
            )
    )
    private String type;

    @EruptField(
            edit = @Edit(
                    title = "유형 값"
            )
    )
    private String value;

    @EruptField(
            edit = @Edit(
                    title = "주문"
            )
    )
    private Integer sort = 0;

    @EruptField(
            edit = @Edit(
                    title = "아이콘",
                    desc = "font-awesome라이브러리 참고"
            )
    )
    private String icon;

    @Column(length = AnnotationConst.CODE_LENGTH)
    @EruptField(
            edit = @Edit(
                    title = "코딩", readonly = @Readonly
            )
    )
    private String code;

    @Column(length = AnnotationConst.REMARK_LENGTH)
    @EruptField(
            edit = @Edit(
                    title = "맞춤 매개변수",
                    desc = "json비즈니스 요구에 따른 컨텍스트, 셀프 서비스 구문 분석을 통해 얻은 형식",
                    type = EditType.CODE_EDITOR,
                    codeEditType = @CodeEditorType(language = "json")
            )
    )
    private String param;

    public EruptMenu() {
    }

    public EruptMenu(String code, String name, String type, String value, Integer status, Integer sort, String icon, EruptMenu parentMenu) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.type = type;
        this.value = value;
        this.sort = sort;
        this.icon = icon;
        this.parentMenu = parentMenu;
        this.setCreateTime(LocalDateTime.now());
    }

    public EruptMenu(String code, String name, String type, String value, EruptMenu parentMenu, Integer sort) {
        this.code = code;
        this.name = name;
        this.parentMenu = parentMenu;
        this.type = type;
        this.value = value;
        this.sort = sort;
        this.setStatus(MenuStatus.OPEN.getValue());
        this.setCreateTime(LocalDateTime.now());
    }

    public static EruptMenu fromMetaMenu(MetaMenu metaMenu) {
        if (null == metaMenu) return null;
        EruptMenu eruptMenu = new EruptMenu(metaMenu.getCode(),
                metaMenu.getName(), null == metaMenu.getType() ? null : metaMenu.getType(),
                metaMenu.getValue(), null == metaMenu.getStatus() ? null : metaMenu.getStatus().getValue(),
                metaMenu.getSort(), metaMenu.getIcon(), fromMetaMenu(metaMenu.getParentMenu()));
        eruptMenu.setId(metaMenu.getId());
        return eruptMenu;
    }

}
