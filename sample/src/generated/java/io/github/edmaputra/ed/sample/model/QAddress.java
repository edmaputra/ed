package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -479525580L;

    public static final QAddress address = new QAddress("address");

    public final io.github.edmaputra.ed.edbase.model.QBaseAddress _super = new io.github.edmaputra.ed.edbase.model.QBaseAddress(this);

    //inherited
    public final StringPath city = _super.city;

    //inherited
    public final StringPath country = _super.country;

    //inherited
    public final DateTimePath<java.time.Instant> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath province = _super.province;

    //inherited
    public final StringPath street = _super.street;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.Instant> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    //inherited
    public final StringPath zipCode = _super.zipCode;

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata metadata) {
        super(Address.class, metadata);
    }

}

