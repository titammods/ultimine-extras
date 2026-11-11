package dev.pedro.ultimineshapes;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public record MaxBlocksPayload(int maxBlocks) implements CustomPacketPayload {
    public static final Type<MaxBlocksPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(UltimineShapes.MOD_ID, "max_blocks"));
    public static final StreamCodec<ByteBuf, MaxBlocksPayload> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(MaxBlocksPayload::new, MaxBlocksPayload::maxBlocks);

    @Override
    public Type<MaxBlocksPayload> type() {
        return TYPE;
    }

    static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToClient(TYPE, STREAM_CODEC, (payload, context) -> ClientShapeFilter.setMaxBlocks(payload.maxBlocks()));
    }
}
