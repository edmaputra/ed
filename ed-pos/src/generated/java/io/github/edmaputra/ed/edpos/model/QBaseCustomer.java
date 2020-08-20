package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseCustomer is a Querydsl query type for BaseCustomer
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseCustomer extends EntityPathBase<BaseCustomer<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -1392210506L;

    public static final QBaseCustomer baseCustomer = new QBaseCustomer("baseCustomer");

    public final QBaseSubject _super = new QBaseSubject(this);

    //inherited
    public final StringPath city = _super.city;

    //inherited
    public final StringPath country = _super.country;

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
    public final SimplePath<java.io.Serializable> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath note = _super.note;

    //inherited
    public final StringPath phone = _super.phone;

    //inherited
    public final StringPath province = _super.province;

    //inherited
    public final StringPath street = _super.street;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    //inherited
    public final StringPath zipCode = _super.zipCode;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCustomer(String variable) {
        super((Class) BaseCustomer.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCustomer(Path<? extends BaseCustomer> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseCustomer(PathMetadata metadata) {
        super((Class) BaseCustomer.class, metadata);
    }

}

