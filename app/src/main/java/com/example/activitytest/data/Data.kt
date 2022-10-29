package com.example.activitytest.data

import android.content.ContentValues
import android.os.Environment
import android.util.Log
import java.io.*


object Data {
    private var trueWeight:CharSequence = "182"
    private var trueHeight:CharSequence = "182"
    private var userName:CharSequence="Lemon"
    private var password:CharSequence="123456"
    private var userDataFile:String = ""
    private var dataDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path + "/PKU_Eater")

    private val fileName = "userData.txt"
    fun getTrueWeight():CharSequence{
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        if(fileExist == -1){//如果文件不存在
            return trueWeight
        }
        //读取文件中的全部内容
        val retval = getContentFromSdcard(userDataFile)
        if(retval != null) {
            val dataPart = retval.split(" ")
            var lastItem:String = ""
            for(item in dataPart) {
                if (lastItem == "weight") {
                    trueWeight = item
                }
                lastItem = item
            }
            trueWeight = retval
        }
        return trueWeight
    }

    fun setTrueWeight(seq:CharSequence){
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件

        trueWeight = seq
        write2File(userDataFile, "weight "+trueWeight.toString()+" ")
    }
    fun getTrueHeight():CharSequence{
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        if(fileExist == -1){//如果文件不存在
            return trueHeight
        }
        //读取文件中的全部内容
        val retval = getContentFromSdcard(userDataFile)
        if(retval != null) {

            val dataPart = retval.split(" ")
            var lastItem:String = ""
            for(item in dataPart) {
                if (lastItem == "height") {
                    trueHeight = item
                }
                lastItem = item
            }
            trueHeight = retval
        }
        return trueHeight
    }
    fun setTrueHeight(seq:CharSequence){
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        trueHeight = seq
        write2File(userDataFile, "height "+trueHeight.toString()+" ")
    }
    fun getUserName():CharSequence{
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        if(fileExist == -1){//如果文件不存在
            return userName
        }
        //读取文件中的全部内容
        val retval = getContentFromSdcard(userDataFile)
        if(retval != null) {

            val dataPart = retval.split(" ")
            var lastItem:String = ""
            for(item in dataPart) {
                if (lastItem == "Username") {
                    userName = item
                }
                lastItem = item
            }
        }
        return userName
    }
    fun getPassword():CharSequence{
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        if(fileExist == -1){//如果文件不存在
            return password
        }
        //读取文件中的全部内容
        val retval = getContentFromSdcard(userDataFile)
        if(retval != null) {
            val dataPart = retval.split(" ")
            var lastItem:String = ""
            for(item in dataPart) {
                if (lastItem == "Password") {
                    password = item
                }
                lastItem = item
            }
        }
        return password
    }

    fun setUserName(seq:CharSequence)
    {
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        userName=seq
        write2File(userDataFile, "Username "+userName.toString()+" ")
    }

    fun setPassword(seq:CharSequence)
    {
        val fileExist = createNewFile(dataDir, fileName)//打开/创建文件
        password=seq
        write2File(userDataFile, "Password "+password.toString()+" ")
    }
    /**
     * 在指定目录下创建文件，若文件不存在，则创建并且返回-1；若已经存在，则不创建且返回0
     */
    fun createNewFile(dirFile: File, fileName: String):Int {
        val file = File(dirFile, fileName)

        if (!dirFile.exists()) {
            //创建目录
            Log.d("File",dirFile.toString())
            dirFile.mkdirs()
        }
        Log.d("file",file.toString())
        userDataFile=file.toString()
        if (!file.exists())
        {
            Log.d("file","Try to Create")
            if(!file.createNewFile())
            {
                Log.d("cookie","Create file Failed")
                return -2
            }
            Log.d("file","Create Success")
            return -1
        }
        return 0
    }

    /**
     * 向指定文件写入指定内容
     */
    fun write2File(destFileName: String, str: String) {
        //LogUtils.d(TAG, "write2File destFileName: $destFileName content: $str")
        val fw = FileWriter(destFileName, true)

        try {
            fw.write(str)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fw.close()
        }
    }

    /**
     * 读取Sdcard文件内容
     * @param context
     * @param filePathAndName 文件完整的路径，包含文件名，如"/sdcard/file.txt
     */
    fun getContentFromSdcard(filePathAndName: String): String? {
        Log.d(ContentValues.TAG, "getContentFromSdcard filePathAndName: $filePathAndName")
        try {
            val fr = FileReader(filePathAndName)
            var bufReader: BufferedReader? = null
            try {
                if (null == fr) {
                    return null
                }
                bufReader = BufferedReader(fr)
                var result = ""
                var line: String? = bufReader.readLine()
                while (line != null) {
                    result += line
                    line = bufReader.readLine()
                }
                //LogUtils.d(TAG, "getContentFromSdcard result: $result")
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //closeSilently(fr)
                //closeSilently(bufReader)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return null
    }



}