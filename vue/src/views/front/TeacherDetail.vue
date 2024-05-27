<template>
  <div class="main-content">
    <div style="width: 60%; margin: 80px auto">
      <div style="display: flex">
        <div style="width: 200px; text-align: center">
          <img :src="infoData.teacherAvatar" alt="" style="width: 200px; height: 200px; border-radius: 50%">
          <div style="margin: 20px 0; font-size: 16px">
            <strong>姓名：</strong> {{infoData.teacherName}}
          </div>
          <div>
            <el-rate v-model="infoData.star" disabled></el-rate>
          </div>
        </div>
        <div style="width: 250px; margin-left: 50px">
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>学校：</strong><span style="color: #666666">{{infoData.school}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>学历：</strong><span style="color: #666666">{{infoData.education}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>专业：</strong><span style="color: #666666">{{infoData.speciality}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>等级：</strong><span style="color: #666666">{{infoData.level}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>生日：</strong><span style="color: #666666">{{infoData.birth}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>邮箱：</strong><span style="color: #666666">{{infoData.teacherEmail}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>电话：</strong><span style="color: #666666">{{infoData.teacherPhone}}</span>
          </div>
        </div>
        <div style="flex: 1; margin-left: 20px">
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>介绍：</strong><span style="color: #666666">{{infoData.description}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>说明：</strong><span style="color: #666666">{{infoData.plan}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>省份：</strong><span style="color: #666666">{{infoData.province}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>地址：</strong><span style="color: #666666">{{infoData.address}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>家教：</strong><span style="color: #666666">{{infoData.typeName}}</span>
          </div>
          <div style="height: 40px; line-height: 40px; font-size: 16px">
            <strong>形式：</strong><span style="color: #666666">{{infoData.type}}</span>
          </div>
        </div>
      </div>
      <div style="margin-top: 40px; text-align: center">
        <el-button type="primary" style="padding: 15px 40px">预约家教</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      infoId: this.$route.query.id,
      infoData: {}
    }
  },
  created() {
    this.loadInfo()
  },
  methods: {
    loadInfo() {
      this.$request.get('/info/selectById/' + this.infoId).then(res => {
        if (res.code === '200') {
          this.infoData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>