package redmine.cybermod.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChauseParticle extends SimpleAnimatedParticle {
    public ChauseParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, spriteWithAge, -0.05F);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.quadSize *= 0.75F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(spriteWithAge);
        if (this.random.nextInt(4) == 0) {
            this.setColor(0.6F + this.random.nextFloat() * 0.3F, 0.6F + this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F);
        } else {
            this.setColor(0.4F + this.random.nextFloat() * 0.3F, 0.1F + this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F);
        }

        this.setBaseAirFriction(0.6F);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ChauseParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
