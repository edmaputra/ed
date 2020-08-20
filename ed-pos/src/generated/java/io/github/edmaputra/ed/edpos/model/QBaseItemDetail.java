package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseItemDetail is a Querydsl query type for BaseItemDetail
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseItemDetail extends EntityPathBase<BaseItemDetail<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 958766364L;

    public static final QBaseItemDetail baseItemDetail = new QBaseItemDetail("baseItemDetail");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdEntity(this);

    public final StringPath barcode = createString("barcode");

    public final NumberPath<java.math.BigDecimal> costPrice = createNumber("costPrice", java.math.BigDecimal.class);

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

    public final DateTimePath<java.time.ZonedDateTime> expiryDate = createDateTime("expiryDate", java.time.ZonedDateTime.class);

    //inherited
    public final SimplePath<java.io.Serializable> id = _super.id;

    //inherited
    public final StringPath note = _super.note;

    public final NumberPath<java.math.BigDecimal> sellPrice = createNumber("sellPrice", java.math.BigDecimal.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final NumberPath<Integer> stockAlert = createNumber("stockAlert", Integer.class);

    public final StringPath stockKeepingUnit = createString("stockKeepingUnit");

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    public final StringPath variant = createString("variant");

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(String variable) {
        super((Class) BaseItemDetail.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(Path<? extends BaseItemDetail> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItemDetail(PathMetadata metadata) {
        super((Class) BaseItemDetail.class, metadata);
    }

}

