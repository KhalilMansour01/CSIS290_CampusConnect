PGDMP      9                }           CampusConnect    17.4    17.4 I               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false                       1262    16386    CampusConnect    DATABASE     u   CREATE DATABASE "CampusConnect" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';
    DROP DATABASE "CampusConnect";
                     postgres    false            �            1255    33348    generate_custom_user_id()    FUNCTION     �  CREATE FUNCTION public.generate_custom_user_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    current_year TEXT;
    next_number INTEGER;
BEGIN
    -- Get last 2 digits of current year
    current_year := TO_CHAR(CURRENT_DATE, 'YY');

    -- Check if a row exists for this year
    SELECT last_number INTO next_number
    FROM user_id_counters
    WHERE year_suffix = current_year;

    IF NOT FOUND THEN
        -- First user for this year
        INSERT INTO user_id_counters (year_suffix, last_number) VALUES (current_year, 1);
        next_number := 1;
    ELSE
        -- Increment the number
        UPDATE user_id_counters
        SET last_number = last_number + 1
        WHERE year_suffix = current_year
        RETURNING last_number INTO next_number;
    END IF;

    -- Format: USR + year + padded number
    NEW.id := 'USR' || current_year || LPAD(next_number::TEXT, 4, '0');
    RETURN NEW;
END;
$$;
 0   DROP FUNCTION public.generate_custom_user_id();
       public               postgres    false            �            1259    24823    announcements    TABLE     p  CREATE TABLE public.announcements (
    id integer NOT NULL,
    club_id integer NOT NULL,
    message text NOT NULL,
    priority character varying(20),
    expiration_date date,
    CONSTRAINT announcements_priority_check CHECK (((priority)::text = ANY ((ARRAY['Normal'::character varying, 'Important'::character varying, 'Urgent'::character varying])::text[])))
);
 !   DROP TABLE public.announcements;
       public         heap r       postgres    false            �            1259    24822    announcements_id_seq    SEQUENCE     �   CREATE SEQUENCE public.announcements_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.announcements_id_seq;
       public               postgres    false    229                        0    0    announcements_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.announcements_id_seq OWNED BY public.announcements.id;
          public               postgres    false    228            �            1259    24766    club_membership    TABLE     �   CREATE TABLE public.club_membership (
    id integer NOT NULL,
    user_id character varying(10) NOT NULL,
    club_id integer NOT NULL,
    role_id integer NOT NULL,
    joined_date date DEFAULT CURRENT_DATE
);
 #   DROP TABLE public.club_membership;
       public         heap r       postgres    false            �            1259    24765    club_membership_id_seq    SEQUENCE     �   CREATE SEQUENCE public.club_membership_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.club_membership_id_seq;
       public               postgres    false    223            !           0    0    club_membership_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.club_membership_id_seq OWNED BY public.club_membership.id;
          public               postgres    false    222            �            1259    24759 
   club_roles    TABLE     j   CREATE TABLE public.club_roles (
    id integer NOT NULL,
    role_name character varying(50) NOT NULL
);
    DROP TABLE public.club_roles;
       public         heap r       postgres    false            �            1259    24758    club_roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.club_roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.club_roles_id_seq;
       public               postgres    false    221            "           0    0    club_roles_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.club_roles_id_seq OWNED BY public.club_roles.id;
          public               postgres    false    220            �            1259    24744    clubs    TABLE     u  CREATE TABLE public.clubs (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    category character varying(50),
    status character varying(20),
    president_id character varying(10),
    CONSTRAINT clubs_status_check CHECK (((status)::text = ANY ((ARRAY['Active'::character varying, 'Inactive'::character varying])::text[])))
);
    DROP TABLE public.clubs;
       public         heap r       postgres    false            �            1259    24743    clubs_id_seq    SEQUENCE     �   CREATE SEQUENCE public.clubs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.clubs_id_seq;
       public               postgres    false    219            #           0    0    clubs_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.clubs_id_seq OWNED BY public.clubs.id;
          public               postgres    false    218            �            1259    24838    event_attendance    TABLE     �   CREATE TABLE public.event_attendance (
    id integer NOT NULL,
    event_id integer NOT NULL,
    user_id character varying(10) NOT NULL,
    attended boolean DEFAULT false,
    check_in_time timestamp without time zone
);
 $   DROP TABLE public.event_attendance;
       public         heap r       postgres    false            �            1259    24837    event_attendance_id_seq    SEQUENCE     �   CREATE SEQUENCE public.event_attendance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.event_attendance_id_seq;
       public               postgres    false    231            $           0    0    event_attendance_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.event_attendance_id_seq OWNED BY public.event_attendance.id;
          public               postgres    false    230            �            1259    24808    events    TABLE     �  CREATE TABLE public.events (
    id integer NOT NULL,
    club_id integer NOT NULL,
    event_name character varying(100) NOT NULL,
    event_date date NOT NULL,
    description text,
    location character varying(100),
    capacity integer,
    status character varying(20),
    CONSTRAINT events_status_check CHECK (((status)::text = ANY ((ARRAY['Pending'::character varying, 'Approved'::character varying, 'Rejected'::character varying])::text[])))
);
    DROP TABLE public.events;
       public         heap r       postgres    false            �            1259    24807    events_id_seq    SEQUENCE     �   CREATE SEQUENCE public.events_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.events_id_seq;
       public               postgres    false    227            %           0    0    events_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.events_id_seq OWNED BY public.events.id;
          public               postgres    false    226            �            1259    24789    membership_requests    TABLE     �  CREATE TABLE public.membership_requests (
    id integer NOT NULL,
    user_id character varying(10) NOT NULL,
    club_id integer NOT NULL,
    status character varying(20),
    request_date date DEFAULT CURRENT_DATE,
    CONSTRAINT membership_requests_status_check CHECK (((status)::text = ANY ((ARRAY['Pending'::character varying, 'Approved'::character varying, 'Rejected'::character varying])::text[])))
);
 '   DROP TABLE public.membership_requests;
       public         heap r       postgres    false            �            1259    24788    membership_requests_id_seq    SEQUENCE     �   CREATE SEQUENCE public.membership_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.membership_requests_id_seq;
       public               postgres    false    225            &           0    0    membership_requests_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.membership_requests_id_seq OWNED BY public.membership_requests.id;
          public               postgres    false    224            �            1259    24856    user_id_counters    TABLE     k   CREATE TABLE public.user_id_counters (
    year_suffix text NOT NULL,
    last_number integer DEFAULT 0
);
 $   DROP TABLE public.user_id_counters;
       public         heap r       postgres    false            �            1259    24732    users    TABLE     +  CREATE TABLE public.users (
    id character varying(10) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password text NOT NULL,
    user_type character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    date_of_birth date,
    CONSTRAINT users_user_type_check CHECK (((user_type)::text = ANY ((ARRAY['Student'::character varying, 'Officer'::character varying, 'OSA_Admin'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap r       postgres    false            P           2604    33349    announcements id    DEFAULT     t   ALTER TABLE ONLY public.announcements ALTER COLUMN id SET DEFAULT nextval('public.announcements_id_seq'::regclass);
 ?   ALTER TABLE public.announcements ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    228    229    229            K           2604    33350    club_membership id    DEFAULT     x   ALTER TABLE ONLY public.club_membership ALTER COLUMN id SET DEFAULT nextval('public.club_membership_id_seq'::regclass);
 A   ALTER TABLE public.club_membership ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    222    223            J           2604    33351    club_roles id    DEFAULT     n   ALTER TABLE ONLY public.club_roles ALTER COLUMN id SET DEFAULT nextval('public.club_roles_id_seq'::regclass);
 <   ALTER TABLE public.club_roles ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    221    220    221            I           2604    33352    clubs id    DEFAULT     d   ALTER TABLE ONLY public.clubs ALTER COLUMN id SET DEFAULT nextval('public.clubs_id_seq'::regclass);
 7   ALTER TABLE public.clubs ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    218    219            Q           2604    33353    event_attendance id    DEFAULT     z   ALTER TABLE ONLY public.event_attendance ALTER COLUMN id SET DEFAULT nextval('public.event_attendance_id_seq'::regclass);
 B   ALTER TABLE public.event_attendance ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    230    231    231            O           2604    33354 	   events id    DEFAULT     f   ALTER TABLE ONLY public.events ALTER COLUMN id SET DEFAULT nextval('public.events_id_seq'::regclass);
 8   ALTER TABLE public.events ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    226    227    227            M           2604    33355    membership_requests id    DEFAULT     �   ALTER TABLE ONLY public.membership_requests ALTER COLUMN id SET DEFAULT nextval('public.membership_requests_id_seq'::regclass);
 E   ALTER TABLE public.membership_requests ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    224    225    225                      0    24823    announcements 
   TABLE DATA           X   COPY public.announcements (id, club_id, message, priority, expiration_date) FROM stdin;
    public               postgres    false    229   }_                 0    24766    club_membership 
   TABLE DATA           U   COPY public.club_membership (id, user_id, club_id, role_id, joined_date) FROM stdin;
    public               postgres    false    223   �_                 0    24759 
   club_roles 
   TABLE DATA           3   COPY public.club_roles (id, role_name) FROM stdin;
    public               postgres    false    221   �_                 0    24744    clubs 
   TABLE DATA           V   COPY public.clubs (id, name, description, category, status, president_id) FROM stdin;
    public               postgres    false    219   `                 0    24838    event_attendance 
   TABLE DATA           Z   COPY public.event_attendance (id, event_id, user_id, attended, check_in_time) FROM stdin;
    public               postgres    false    231   �`                 0    24808    events 
   TABLE DATA           n   COPY public.events (id, club_id, event_name, event_date, description, location, capacity, status) FROM stdin;
    public               postgres    false    227   �`                 0    24789    membership_requests 
   TABLE DATA           Y   COPY public.membership_requests (id, user_id, club_id, status, request_date) FROM stdin;
    public               postgres    false    225   a                 0    24856    user_id_counters 
   TABLE DATA           D   COPY public.user_id_counters (year_suffix, last_number) FROM stdin;
    public               postgres    false    232   Aa       
          0    24732    users 
   TABLE DATA           q   COPY public.users (id, first_name, last_name, email, password, user_type, created_at, date_of_birth) FROM stdin;
    public               postgres    false    217   ca       '           0    0    announcements_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.announcements_id_seq', 1, true);
          public               postgres    false    228            (           0    0    club_membership_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.club_membership_id_seq', 1, true);
          public               postgres    false    222            )           0    0    club_roles_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.club_roles_id_seq', 1, true);
          public               postgres    false    220            *           0    0    clubs_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.clubs_id_seq', 3, true);
          public               postgres    false    218            +           0    0    event_attendance_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.event_attendance_id_seq', 1, true);
          public               postgres    false    230            ,           0    0    events_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.events_id_seq', 1, true);
          public               postgres    false    226            -           0    0    membership_requests_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.membership_requests_id_seq', 1, true);
          public               postgres    false    224            h           2606    24831     announcements announcements_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.announcements
    ADD CONSTRAINT announcements_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.announcements DROP CONSTRAINT announcements_pkey;
       public                 postgres    false    229            b           2606    24772 $   club_membership club_membership_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.club_membership
    ADD CONSTRAINT club_membership_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.club_membership DROP CONSTRAINT club_membership_pkey;
       public                 postgres    false    223            `           2606    24764    club_roles club_roles_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.club_roles
    ADD CONSTRAINT club_roles_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.club_roles DROP CONSTRAINT club_roles_pkey;
       public                 postgres    false    221            ^           2606    24752    clubs clubs_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.clubs DROP CONSTRAINT clubs_pkey;
       public                 postgres    false    219            j           2606    24844 &   event_attendance event_attendance_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.event_attendance
    ADD CONSTRAINT event_attendance_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.event_attendance DROP CONSTRAINT event_attendance_pkey;
       public                 postgres    false    231            f           2606    24816    events events_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public                 postgres    false    227            d           2606    24796 ,   membership_requests membership_requests_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.membership_requests
    ADD CONSTRAINT membership_requests_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.membership_requests DROP CONSTRAINT membership_requests_pkey;
       public                 postgres    false    225            l           2606    24863 &   user_id_counters user_id_counters_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.user_id_counters
    ADD CONSTRAINT user_id_counters_pkey PRIMARY KEY (year_suffix);
 P   ALTER TABLE ONLY public.user_id_counters DROP CONSTRAINT user_id_counters_pkey;
       public                 postgres    false    232            Z           2606    24742    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public                 postgres    false    217            \           2606    24740    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    217            w           2620    33356    users before_insert_user    TRIGGER     �   CREATE TRIGGER before_insert_user BEFORE INSERT ON public.users FOR EACH ROW EXECUTE FUNCTION public.generate_custom_user_id();
 1   DROP TRIGGER before_insert_user ON public.users;
       public               postgres    false    217    233            x           2620    33357    users set_custom_user_id    TRIGGER     �   CREATE TRIGGER set_custom_user_id BEFORE INSERT ON public.users FOR EACH ROW WHEN ((new.id IS NULL)) EXECUTE FUNCTION public.generate_custom_user_id();
 1   DROP TRIGGER set_custom_user_id ON public.users;
       public               postgres    false    217    217    233            t           2606    24832 "   announcements fk_announcement_club    FK CONSTRAINT     �   ALTER TABLE ONLY public.announcements
    ADD CONSTRAINT fk_announcement_club FOREIGN KEY (club_id) REFERENCES public.clubs(id);
 L   ALTER TABLE ONLY public.announcements DROP CONSTRAINT fk_announcement_club;
       public               postgres    false    4702    219    229            u           2606    24845 $   event_attendance fk_attendance_event    FK CONSTRAINT     �   ALTER TABLE ONLY public.event_attendance
    ADD CONSTRAINT fk_attendance_event FOREIGN KEY (event_id) REFERENCES public.events(id);
 N   ALTER TABLE ONLY public.event_attendance DROP CONSTRAINT fk_attendance_event;
       public               postgres    false    231    4710    227            v           2606    24850 #   event_attendance fk_attendance_user    FK CONSTRAINT     �   ALTER TABLE ONLY public.event_attendance
    ADD CONSTRAINT fk_attendance_user FOREIGN KEY (user_id) REFERENCES public.users(id);
 M   ALTER TABLE ONLY public.event_attendance DROP CONSTRAINT fk_attendance_user;
       public               postgres    false    231    4700    217            n           2606    24778 '   club_membership fk_club_membership_club    FK CONSTRAINT     �   ALTER TABLE ONLY public.club_membership
    ADD CONSTRAINT fk_club_membership_club FOREIGN KEY (club_id) REFERENCES public.clubs(id);
 Q   ALTER TABLE ONLY public.club_membership DROP CONSTRAINT fk_club_membership_club;
       public               postgres    false    4702    219    223            o           2606    24783 '   club_membership fk_club_membership_role    FK CONSTRAINT     �   ALTER TABLE ONLY public.club_membership
    ADD CONSTRAINT fk_club_membership_role FOREIGN KEY (role_id) REFERENCES public.club_roles(id);
 Q   ALTER TABLE ONLY public.club_membership DROP CONSTRAINT fk_club_membership_role;
       public               postgres    false    223    4704    221            p           2606    24773 '   club_membership fk_club_membership_user    FK CONSTRAINT     �   ALTER TABLE ONLY public.club_membership
    ADD CONSTRAINT fk_club_membership_user FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.club_membership DROP CONSTRAINT fk_club_membership_user;
       public               postgres    false    223    217    4700            m           2606    24753    clubs fk_club_president    FK CONSTRAINT     {   ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT fk_club_president FOREIGN KEY (president_id) REFERENCES public.users(id);
 A   ALTER TABLE ONLY public.clubs DROP CONSTRAINT fk_club_president;
       public               postgres    false    217    219    4700            s           2606    24817    events fk_event_club    FK CONSTRAINT     s   ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_event_club FOREIGN KEY (club_id) REFERENCES public.clubs(id);
 >   ALTER TABLE ONLY public.events DROP CONSTRAINT fk_event_club;
       public               postgres    false    219    227    4702            q           2606    24802 #   membership_requests fk_request_club    FK CONSTRAINT     �   ALTER TABLE ONLY public.membership_requests
    ADD CONSTRAINT fk_request_club FOREIGN KEY (club_id) REFERENCES public.clubs(id);
 M   ALTER TABLE ONLY public.membership_requests DROP CONSTRAINT fk_request_club;
       public               postgres    false    4702    225    219            r           2606    24797 #   membership_requests fk_request_user    FK CONSTRAINT     �   ALTER TABLE ONLY public.membership_requests
    ADD CONSTRAINT fk_request_user FOREIGN KEY (user_id) REFERENCES public.users(id);
 M   ALTER TABLE ONLY public.membership_requests DROP CONSTRAINT fk_request_user;
       public               postgres    false    225    217    4700               (   x�3�4�,I-.���/�M��4202�50�56������ �#L         %   x�3�225000�A##S]#]CK�=... bv{            x�3�(J-�LI�+����� .��         ^   x�3�O,�KU ��!��
@���R��[���S���Y�Z\��\�Y��ddj```�e/�kq�:8�r�]�1�1gbb"	:�b���� ��1�             x�3�4�225000�,������� ;y         B   x�3�4�,I-.QH-K�+�4202�5 !�hJjqrQfAIf~g��;����cAAQ~Yj
W� iWx         .   x�3�225000�4�H�K��K�4202�50�50����� ��
            x�32�4����� �W      
   �   x�m�K
�0�u<E/`�I|�꺴�Zw��&Ti�����V��BB��0�L�ߙ  $�F�֐��c?�D�}�it���H��j��^���MJ[G0!:`�r�BD#�g��?QP���<����<�}�ݪlz�I?���6�gU����&3qʐB����k�;ŉ�r��[W��"���KI� � NUq     