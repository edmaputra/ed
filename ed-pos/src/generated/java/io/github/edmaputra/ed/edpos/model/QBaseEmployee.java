package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEmployee is a Querydsl query type for BaseEmployee
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseEmployee extends EntityPathBase<BaseEmployee<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -804916090L;

    public static final QBaseEmployee baseEmployee = new QBaseEmployee("baseEmployee");

    public final io.github.edmaputra.ed.edbase.model.QBasePerson _super = new io.github.edmaputra.ed.edbase.model.QBasePerson(this);

    //inherited
    public final DatePath<java.time.LocalDate> birthDate = _super.birthDate;

    //inherited
    public final StringPath birthPlace = _super.birthPlace;

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

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final EnumPath<io.github.edmaputra.ed.edbase.model.Gender> gender = _super.gender;

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final EnumPath<io.github.edmaputra.ed.edbase.model.MaritalStatus> maritalStatus = _super.maritalStatus;

    //inherited
    public final StringPath middleName = _super.middleName;

    //inherited
    public final StringPath note = _super.note;

    //inherited
    public final StringPath phoneNumber = _super.phoneNumber;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEmployee(String variable) {
        super((Class) BaseEmployee.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEmployee(Path<? extends BaseEmployee> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEmployee(PathMetadata metadata) {
        super((Class) BaseEmployee.class, metadata);
    }

}

