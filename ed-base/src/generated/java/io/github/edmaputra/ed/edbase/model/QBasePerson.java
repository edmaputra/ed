package io.github.edmaputra.ed.edbase.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasePerson is a Querydsl query type for BasePerson
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBasePerson extends EntityPathBase<BasePerson<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 1676335808L;

    public static final QBasePerson basePerson = new QBasePerson("basePerson");

    public final QBaseIdEntity _super = new QBaseIdEntity(this);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath birthPlace = createString("birthPlace");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final StringPath deleteBy = _super.deleteBy;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> deleteTime = _super.deleteTime;

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    public final StringPath lastName = createString("lastName");

    public final EnumPath<MaritalStatus> maritalStatus = createEnum("maritalStatus", MaritalStatus.class);

    public final StringPath middleName = createString("middleName");

    //inherited
    public final StringPath note = _super.note;

    public final StringPath phoneNumber = createString("phoneNumber");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBasePerson(String variable) {
        super((Class) BasePerson.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBasePerson(Path<? extends BasePerson> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBasePerson(PathMetadata metadata) {
        super((Class) BasePerson.class, metadata);
    }

}

