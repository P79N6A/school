package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/4/2 17:56
 * @des ${TODO}
 */

public class PlayerVideoBean {


    /**
     * playUrl : https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-aa4739342224fcb8cf1ea2cfe703ac0e-ld.m3u8
     * cover : https://sp.idyoga.cn/snapshot/b9cf1aaeec27416783968672f861d9b200001.jpg
     * returnInfo : {"RequestId":"DA27F163-8243-4AD0-B17F-848A413D5F10","VideoBase":{"TranscodeMode":"FastTranscode","CreationTime":"2019-03-11T08:37:40Z","CoverURL":"https://sp.idyoga.cn/snapshot/b9cf1aaeec27416783968672f861d9b200001.jpg","Status":"Normal","MediaType":"video","VideoId":"b9cf1aaeec27416783968672f861d9b2","Duration":"1.873","OutputType":"cdn","Title":"测试文件上传：06546"},"PlayInfoList":{"PlayInfo":[{"Format":"m3u8","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":960,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-aa4739342224fcb8cf1ea2cfe703ac0e-ld.m3u8","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.9264","JobId":"7f032e3b0bcb472ba628e47937e842c2","Encrypt":0,"Width":462,"Fps":"25","Bitrate":"732.308","Size":176344,"Definition":"LD"},{"Format":"m3u8","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":1280,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-446a9327b4bb98724abb68ef6b520f15-sd.m3u8","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.9264","JobId":"d8a2e39a9b0848b5bc7d2062394f2d2f","Encrypt":0,"Width":616,"Fps":"25","Bitrate":"1278.808","Size":307944,"Definition":"SD"},{"Format":"mp4","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":960,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-37181255b44331c9d37cbb9310efd765-ld.mp4","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.8800","JobId":"f03cdc06fdb04897bea1079e9d669a7a","Encrypt":0,"Width":462,"Fps":"25","Bitrate":"688.727","Size":161851,"Definition":"LD"},{"Format":"mp4","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":1280,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-070ab62a3ede87aef40b587048e00cca-sd.mp4","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.8800","JobId":"7b2c15548c7541538ceab6d7ba07f60e","Encrypt":0,"Width":616,"Fps":"25","Bitrate":"1259.4","Size":295959,"Definition":"SD"}]}}
     */

    private String playUrl;
    private String cover;
    private ReturnInfoBean returnInfo;

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public ReturnInfoBean getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(ReturnInfoBean returnInfo) {
        this.returnInfo = returnInfo;
    }

    public static class ReturnInfoBean {
        /**
         * RequestId : DA27F163-8243-4AD0-B17F-848A413D5F10
         * VideoBase : {"TranscodeMode":"FastTranscode","CreationTime":"2019-03-11T08:37:40Z","CoverURL":"https://sp.idyoga.cn/snapshot/b9cf1aaeec27416783968672f861d9b200001.jpg","Status":"Normal","MediaType":"video","VideoId":"b9cf1aaeec27416783968672f861d9b2","Duration":"1.873","OutputType":"cdn","Title":"测试文件上传：06546"}
         * PlayInfoList : {"PlayInfo":[{"Format":"m3u8","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":960,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-aa4739342224fcb8cf1ea2cfe703ac0e-ld.m3u8","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.9264","JobId":"7f032e3b0bcb472ba628e47937e842c2","Encrypt":0,"Width":462,"Fps":"25","Bitrate":"732.308","Size":176344,"Definition":"LD"},{"Format":"m3u8","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":1280,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-446a9327b4bb98724abb68ef6b520f15-sd.m3u8","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.9264","JobId":"d8a2e39a9b0848b5bc7d2062394f2d2f","Encrypt":0,"Width":616,"Fps":"25","Bitrate":"1278.808","Size":307944,"Definition":"SD"},{"Format":"mp4","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":960,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-37181255b44331c9d37cbb9310efd765-ld.mp4","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.8800","JobId":"f03cdc06fdb04897bea1079e9d669a7a","Encrypt":0,"Width":462,"Fps":"25","Bitrate":"688.727","Size":161851,"Definition":"LD"},{"Format":"mp4","StreamType":"video","PreprocessStatus":"UnPreprocess","ModificationTime":"2019-03-11T08:38:51Z","Height":1280,"PlayURL":"https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-070ab62a3ede87aef40b587048e00cca-sd.mp4","NarrowBandType":"0","CreationTime":"2019-03-11T08:38:49Z","Status":"Normal","Duration":"1.8800","JobId":"7b2c15548c7541538ceab6d7ba07f60e","Encrypt":0,"Width":616,"Fps":"25","Bitrate":"1259.4","Size":295959,"Definition":"SD"}]}
         */

        private String RequestId;
        private VideoBaseBean VideoBase;
        private PlayInfoListBean PlayInfoList;

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public VideoBaseBean getVideoBase() {
            return VideoBase;
        }

        public void setVideoBase(VideoBaseBean VideoBase) {
            this.VideoBase = VideoBase;
        }

        public PlayInfoListBean getPlayInfoList() {
            return PlayInfoList;
        }

        public void setPlayInfoList(PlayInfoListBean PlayInfoList) {
            this.PlayInfoList = PlayInfoList;
        }

        public static class VideoBaseBean {
            /**
             * TranscodeMode : FastTranscode
             * CreationTime : 2019-03-11T08:37:40Z
             * CoverURL : https://sp.idyoga.cn/snapshot/b9cf1aaeec27416783968672f861d9b200001.jpg
             * Status : Normal
             * MediaType : video
             * VideoId : b9cf1aaeec27416783968672f861d9b2
             * Duration : 1.873
             * OutputType : cdn
             * Title : 测试文件上传：06546
             */

            private String TranscodeMode;
            private String CreationTime;
            private String CoverURL;
            private String Status;
            private String MediaType;
            private String VideoId;
            private String Duration;
            private String OutputType;
            private String Title;

            public String getTranscodeMode() {
                return TranscodeMode;
            }

            public void setTranscodeMode(String TranscodeMode) {
                this.TranscodeMode = TranscodeMode;
            }

            public String getCreationTime() {
                return CreationTime;
            }

            public void setCreationTime(String CreationTime) {
                this.CreationTime = CreationTime;
            }

            public String getCoverURL() {
                return CoverURL;
            }

            public void setCoverURL(String CoverURL) {
                this.CoverURL = CoverURL;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getMediaType() {
                return MediaType;
            }

            public void setMediaType(String MediaType) {
                this.MediaType = MediaType;
            }

            public String getVideoId() {
                return VideoId;
            }

            public void setVideoId(String VideoId) {
                this.VideoId = VideoId;
            }

            public String getDuration() {
                return Duration;
            }

            public void setDuration(String Duration) {
                this.Duration = Duration;
            }

            public String getOutputType() {
                return OutputType;
            }

            public void setOutputType(String OutputType) {
                this.OutputType = OutputType;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }
        }

        public static class PlayInfoListBean {
            private List<PlayInfoBean> PlayInfo;

            public List<PlayInfoBean> getPlayInfo() {
                return PlayInfo;
            }

            public void setPlayInfo(List<PlayInfoBean> PlayInfo) {
                this.PlayInfo = PlayInfo;
            }

            public static class PlayInfoBean {
                /**
                 * Format : m3u8
                 * StreamType : video
                 * PreprocessStatus : UnPreprocess
                 * ModificationTime : 2019-03-11T08:38:51Z
                 * Height : 960
                 * PlayURL : https://sp.idyoga.cn/b9cf1aaeec27416783968672f861d9b2/add0c2ed0e3a4e14a4f76d99c38e4904-aa4739342224fcb8cf1ea2cfe703ac0e-ld.m3u8
                 * NarrowBandType : 0
                 * CreationTime : 2019-03-11T08:38:49Z
                 * Status : Normal
                 * Duration : 1.9264
                 * JobId : 7f032e3b0bcb472ba628e47937e842c2
                 * Encrypt : 0
                 * Width : 462
                 * Fps : 25
                 * Bitrate : 732.308
                 * Size : 176344
                 * Definition : LD
                 */

                private String Format;
                private String StreamType;
                private String PreprocessStatus;
                private String ModificationTime;
                private int Height;
                private String PlayURL;
                private String NarrowBandType;
                private String CreationTime;
                private String Status;
                private String Duration;
                private String JobId;
                private int Encrypt;
                private int Width;
                private String Fps;
                private String Bitrate;
                private int Size;
                private String Definition;

                public String getFormat() {
                    return Format;
                }

                public void setFormat(String Format) {
                    this.Format = Format;
                }

                public String getStreamType() {
                    return StreamType;
                }

                public void setStreamType(String StreamType) {
                    this.StreamType = StreamType;
                }

                public String getPreprocessStatus() {
                    return PreprocessStatus;
                }

                public void setPreprocessStatus(String PreprocessStatus) {
                    this.PreprocessStatus = PreprocessStatus;
                }

                public String getModificationTime() {
                    return ModificationTime;
                }

                public void setModificationTime(String ModificationTime) {
                    this.ModificationTime = ModificationTime;
                }

                public int getHeight() {
                    return Height;
                }

                public void setHeight(int Height) {
                    this.Height = Height;
                }

                public String getPlayURL() {
                    return PlayURL;
                }

                public void setPlayURL(String PlayURL) {
                    this.PlayURL = PlayURL;
                }

                public String getNarrowBandType() {
                    return NarrowBandType;
                }

                public void setNarrowBandType(String NarrowBandType) {
                    this.NarrowBandType = NarrowBandType;
                }

                public String getCreationTime() {
                    return CreationTime;
                }

                public void setCreationTime(String CreationTime) {
                    this.CreationTime = CreationTime;
                }

                public String getStatus() {
                    return Status;
                }

                public void setStatus(String Status) {
                    this.Status = Status;
                }

                public String getDuration() {
                    return Duration;
                }

                public void setDuration(String Duration) {
                    this.Duration = Duration;
                }

                public String getJobId() {
                    return JobId;
                }

                public void setJobId(String JobId) {
                    this.JobId = JobId;
                }

                public int getEncrypt() {
                    return Encrypt;
                }

                public void setEncrypt(int Encrypt) {
                    this.Encrypt = Encrypt;
                }

                public int getWidth() {
                    return Width;
                }

                public void setWidth(int Width) {
                    this.Width = Width;
                }

                public String getFps() {
                    return Fps;
                }

                public void setFps(String Fps) {
                    this.Fps = Fps;
                }

                public String getBitrate() {
                    return Bitrate;
                }

                public void setBitrate(String Bitrate) {
                    this.Bitrate = Bitrate;
                }

                public int getSize() {
                    return Size;
                }

                public void setSize(int Size) {
                    this.Size = Size;
                }

                public String getDefinition() {
                    return Definition;
                }

                public void setDefinition(String Definition) {
                    this.Definition = Definition;
                }
            }
        }
    }
}
