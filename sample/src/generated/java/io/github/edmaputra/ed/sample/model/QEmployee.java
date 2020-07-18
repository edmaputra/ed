package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 431793518L;

    public static final QEmployee employee = new QEmployee("employee");

    public final io.github.edmaputra.ed.core.model.QBasePerson _super = new io.github.edmaputra.ed.core.model.QBasePerson(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    //inherited
    public final DatePath<java.time.LocalDate> birthDate = _super.birthDate;

    //inherited
    public final StringPath birthPlace = _super.birthPlace;

    //inherited
    public final DateTimePath<java.time.Instant> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final EnumPath<io.github.edmaputra.ed.core.model.Gender> gender = _super.gender;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final EnumPath<io.github.edmaputra.ed.core.model.MaritalStatus> maritalStatus = _super.maritalStatus;

    //inherited
    public final StringPath middleName = _super.middleName;

    //inherited
    public final StringPath phoneNumber = _super.phoneNumber;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.Instant> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

