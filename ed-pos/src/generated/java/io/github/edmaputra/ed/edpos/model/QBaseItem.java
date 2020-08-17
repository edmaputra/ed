package io.github.edmaputra.ed.edpos.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseItem is a Querydsl query type for BaseItem
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseItem extends EntityPathBase<BaseItem<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -944425749L;

    public static final QBaseItem baseItem = new QBaseItem("baseItem");

    public final io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity _super = new io.github.edmaputra.ed.edbase.model.QBaseIdAndNameEntity(this);

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
    public final SimplePath<java.io.Serializable> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(String variable) {
        super((Class) BaseItem.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(Path<? extends BaseItem> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseItem(PathMetadata metadata) {
        super((Class) BaseItem.class, metadata);
    }

}

