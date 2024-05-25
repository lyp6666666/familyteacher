<template>
  <div class="main-content">
    <div style="width: 75%;margin: 10px auto;min-height: 1000px">
      <div style="display: flex;height: 100px">
        <div style="width: 220px; height: 250px; border: 1px solid #cccccc ;border-radius: 5px;padding: 10px ">
          <el-row :gutter="10" style="padding: 5px">
            <el-col :span="8" style="padding: 0 5px;cursor: pointer" v-for="item in baseTypeData" :key="item">
              <img :src="item.img" alt="" style="width: 50px">
              <div style="font-size: 12px;text-align: center">{{item.name}}</div>
            </el-col>

          </el-row>
        </div>
        <div style="flex: 1;height: 250px;margin: 0 7px;border-radius: 5px">
          <el-carousel height="250px" style="border-radius: 5px">
            <el-carousel-item v-for="item in carouselData" :key="item">
              <img :src="item" alt="" style="width: 100%;height: 250px;border-radius: 5px">
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="width: 230px;height: 250px;border: 1px solid #cccccc;border-radius: 5px;text-align: center;padding: 30px 0">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 80px;height: 80px">
          <div style="margin-top: 15px">
            <el-button style="padding: 10px 50px" type="success">家长登录</el-button>
          </div>
          <div style="margin-top: 10px">
            <el-button style="padding: 10px 50px" type="danger">教员登录</el-button>
          </div>
        </div>

      </div>
      <div style="display: flex;height: 100px;margin-top: 160px" >
        <div style="width: 220px; height: 170px; border: 1px solid #cccccc ;border-radius: 5px;padding: 10px ">
          <el-row :gutter="10" style="padding: 5px">
            <el-col :span="8" style="padding: 0 5px;cursor: pointer" v-for="item in excellentTypeData" :key="item">
              <img :src="item.img" alt="" style="width: 50px">
              <div style="font-size: 12px;text-align: center">{{item.name}}</div>
            </el-col>

          </el-row>
        </div>
        <div style="flex: 1;height: 170px;margin: 0 7px;border-radius: 5px">
          <el-row :gutter="5">
            <el-col :span="12">
              <img src="@/assets/imgs/qingjiajiao.png" alt="" style="width:100%;height: 170px">
            </el-col>
            <el-col :span="12">
              <img src="@/assets/imgs/zuojiajiao.png" alt="" style="width:100%;height: 170px">
            </el-col>
          </el-row>

        </div>
        <div style="width: 230px;height: 170px;border: 1px solid #cccccc;border-radius: 5px;text-align: center;padding: 5px 0">
          <img src="@/assets/imgs/114c337f57ec2e08584368907c03730.jpg" alt="" style="height: 130px;width: 130px;padding: 5px">
          <div style="color: #666666">加帅哥微信，享成功人生</div>
        </div>

      </div>
      <div style="margin-top: 100px; border-left: 5px solid #83da83; font-size: 20px;padding: 5px 10px;font-weight: bold">
        平台教员
      </div>
      <div style="margin-top: 30px">
        <el-row :gutter="30">
          <el-col :span="4" style="text-align: center;margin-bottom: 20px" v-for="item in infoData">
            <div class="card">
              <img :src="item.teacherAvatar" alt="" style="width: 100px;height: 100px;border-radius: 50%">
              <div style="font-weight: bold">{{ item.teacherName }}</div>
              <div style="margin-top: 5px;color: #666666">{{ item.speciality }}</div>
              <div style="margin-top: 5px;color: #666666">{{ item.school }}</div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>


  </div>
</template>

<script>

export default {

  data() {
    return {
      baseTypeData:[],
      excellentTypeData:[],
      carouselData:[
          require('@/assets/imgs/lun-1.jpg'),
          require('@/assets/imgs/lun-2.jpg'),
          require('@/assets/imgs/lun-3.jpg'),
          require('@/assets/imgs/lun-4.jpg')
      ],
      infoData:[],
    }
  },
  mounted() {
    this.loadBaseType()
    this.loadExcellentTypeData()
    this.loadInfo()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadInfo(){
      this.$request.get('/info/selectAll',{
        params:{
          status:'审核通过'
        }
      }).then(res=>{
        if(res.code === '200'){
          this.infoData=res.data
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    loadBaseType(){
      this.$request.get('/type/selectAll',{
        params:{
          type:'普通文化课'
        }
      }).then(res=>{
        if (res.code ==='200'){
          this.baseTypeData = res.data
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    loadExcellentTypeData(){
      this.$request.get('/type/selectAll',{
        params:{
          type:'兴趣拔高课'
        }
      }).then(res=>{
        if (res.code ==='200'){
          this.excellentTypeData = res.data
        }else{
          this.$message.error(res.msg)
        }
      })
    }

  }
}
</script>
<style>
.card {
  padding: 5px;
  cursor: pointer;
  transition: all 0.3s ease; /* 添加平滑过渡效果 */
}

.card:hover {
  background-color: #dbf1e1;
  transform: scale(1.1); /* 鼠标悬停时放大图片 */
}

</style>
