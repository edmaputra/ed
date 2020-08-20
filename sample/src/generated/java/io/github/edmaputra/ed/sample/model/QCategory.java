package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -711164994L;

    public static final QCategory category = new QCategory("category");

    public final io.github.edmaputra.ed.edpos.model.QBaseCategory _super = new io.github.edmaputra.ed.edpos.model.QBaseCategory(this);

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

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final SetPath<Item, QItem> items = this.<Item, QItem>createSet("items", Item.class, QItem.class, PathInits.DIRECT2);

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath note = _super.note;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath version = _super.version;

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

