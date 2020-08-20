package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseSupplier is a Querydsl query type for BaseSupplier
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseSupplier extends EntityPathBase<BaseSupplier<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 633276324L;

    public static final QBaseSupplier baseSupplier = new QBaseSupplier("baseSupplier");

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
    public QBaseSupplier(String variable) {
        super((Class) BaseSupplier.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSupplier(Path<? extends BaseSupplier> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseSupplier(PathMetadata metadata) {
        super((Class) BaseSupplier.class, metadata);
    }

}

