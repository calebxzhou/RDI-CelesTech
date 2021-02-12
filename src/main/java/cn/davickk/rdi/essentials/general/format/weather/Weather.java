package cn.davickk.rdi.essentials.general.format.weather;

public class Weather {
    public Result result;

    public class Result {
        public Daily daily;

        public class Daily {
            public Skycon[] skycon;
            public Temperature[] temperature;
            public Precipitation[] precipitation;
            public Humidity[] humidity;

            public class Skycon //����[0]�ǵ���
            {
                public String date;
                public String value;
            }

            public class Temperature //����[0]�ǵ���
            {
                public String date;
                public double max;
                public double min;
            }

            public class Precipitation//����[0]�ǵ���
            {
                public String date;
                public double max;
                public double min;
            }

            public class Pressure//����[0]�ǵ���
            {
                public String date;
                public double max;
                public double min;
            }

            public class Humidity {
                public String date;
                public double max;
                public double min;
            }

            public class Wind//����[0]�ǵ���
            {
                public String date;

                public class Max {
                    public double direction;
                    public double speed;
                }

                public class Min {
                    public double direction;
                    public double speed;
                }
            }
        }
    }
}
